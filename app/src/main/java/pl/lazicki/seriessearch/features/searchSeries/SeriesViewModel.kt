package pl.lazicki.seriessearch.features.searchSeries

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class SeriesViewModel: ViewModel() {

    private val _searchUiState = MutableLiveData<SearchUiState>()
    val searchUiState: LiveData<SearchUiState> = _searchUiState

    fun search(query: String) {

    }
}

sealed class SearchUiState {
    object Loading: SearchUiState()
    data class Success(val series: List<SeriesDisplayable>): SearchUiState()
    data class Error(val message: String): SearchUiState()
}