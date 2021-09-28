package pl.lazicki.seriessearch.series

import androidx.lifecycle.Observer
import androidx.lifecycle.viewModelScope
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import org.amshove.kluent.shouldBe
import org.junit.jupiter.api.Test
import pl.lazicki.seriessearch.features.searchSeries.domain.SearchUseCase
import pl.lazicki.seriessearch.features.searchSeries.domain.model.Serie
import pl.lazicki.seriessearch.features.searchSeries.presentation.SearchUiState
import pl.lazicki.seriessearch.features.searchSeries.presentation.SeriesViewModel
import pl.lazicki.seriessearch.features.searchSeries.presentation.model.SerieDisplayable
import pl.lazicki.seriessearch.mocks.mock
import pl.lazicki.seriessearch.utils.ViewModelTest
import pl.lazicki.seriessearch.utils.getOrAwaitValue
import pl.lazicki.seriessearch.utils.observeForTesting

class SeriesViewModelTest : ViewModelTest() {

    @Test
    fun `WHEN search method is invoked THEN set Loading Ui state`() {
        //given
        val useCase = mockk<SearchUseCase>(relaxed = true)
        val viewModel = SeriesViewModel(useCase)
        val query = "The Good Doctor"

        //when
        viewModel.search(query)

        //then
        viewModel.searchUiState.value shouldBe SearchUiState.Loading
    }

    @Test
    fun `WHEN search method is invoked THEN invoke use case to get search results`() {
        //given
        val useCase = mockk<SearchUseCase>(relaxed = true)
        val viewModel = SeriesViewModel(useCase)
        val query = "The Good Doctor"

        //when
        viewModel.search(query)

        //then
        verify { useCase(query, viewModel.viewModelScope, any(), any()) }
    }

    @Test
    fun `GIVEN use case result is success WHEN search method is invoked THEN set Success Ui state AND set result in live data`() {
        //given
        val series =
            listOf(SerieDisplayable.mock(), SerieDisplayable.mock(), SerieDisplayable.mock())
        val useCase = mockk<SearchUseCase> {
            every { this@mockk(any(), any(), any(), any()) } answers {
                lastArg<(Result<List<SerieDisplayable>>) -> Unit>()(Result.success(series))
            }
        }
        val viewModel = SeriesViewModel(useCase)
        val query = "The Good Doctor"

        //when
        viewModel.search(query)

        //then
        (viewModel.searchUiState.value as SearchUiState.Success)
            .series
            .forEachIndexed { index, serieDisplayable ->
                val serie = series[index]
                serieDisplayable.name shouldBe serie.name
                serieDisplayable.genres shouldBe serie.genres
                serieDisplayable.image shouldBe serie.image
            }
    }
}