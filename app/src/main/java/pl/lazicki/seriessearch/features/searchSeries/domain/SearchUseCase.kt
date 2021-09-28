package pl.lazicki.seriessearch.features.searchSeries.domain

import pl.lazicki.seriessearch.core.base.UseCase
import pl.lazicki.seriessearch.features.searchSeries.presentation.model.SerieDisplayable
import pl.lazicki.seriessearch.features.searchSeries.domain.model.mapToPresentation

class SearchUseCase(private val repository: SeriesRepository): UseCase<List<SerieDisplayable>, String>() {

    override suspend fun action(params: String): List<SerieDisplayable> =
        repository.searchSeries(params)
            .map { it.mapToPresentation() }
}