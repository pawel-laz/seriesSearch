package pl.lazicki.seriessearch.features.searchSeries.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import pl.lazicki.seriessearch.features.searchSeries.presentation.model.SerieDisplayable
import pl.lazicki.seriessearch.features.searchSeries.domain.SearchUseCase

class SeriesViewModel(
    private val searchUseCase: SearchUseCase
) : ViewModel() {

    private val _searchUiState = MutableLiveData<SearchUiState>()
    val searchUiState: LiveData<SearchUiState> = _searchUiState

    fun search(query: String) {
        _searchUiState.value = SearchUiState.Loading
        searchUseCase(query, viewModelScope) { result ->
            result.onSuccess { series -> _searchUiState.value = SearchUiState.Success(series) }
            result.onFailure { error -> _searchUiState.value = SearchUiState.Error(error.message) }
        }
    }
}

sealed class SearchUiState {
    object Loading : SearchUiState()
    data class Success(val series: List<SerieDisplayable>?) : SearchUiState()
    data class Error(val message: String?) : SearchUiState()
}