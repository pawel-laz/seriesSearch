package pl.lazicki.seriessearch.series

import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.GlobalScope
import org.junit.jupiter.api.Test
import pl.lazicki.seriessearch.features.searchSeries.domain.SearchUseCase
import pl.lazicki.seriessearch.features.searchSeries.domain.SeriesRepository

internal class SearchUseCaseTest {

    @Test
    fun `WHEN use case is invoked THAN execute searchSeries method from repository`() {
        //given
        val repository = mockk<SeriesRepository>(relaxed = true)
        val useCase = SearchUseCase(repository)
        val query = "The Good Doctor"

        //when
        useCase(
            params = query,
            viewModelScope = GlobalScope //Should be replaced with TestCoroutineScope when it will be in a stable channel
        )

        //than
        coVerify { repository.searchSeries(query) }
    }
}