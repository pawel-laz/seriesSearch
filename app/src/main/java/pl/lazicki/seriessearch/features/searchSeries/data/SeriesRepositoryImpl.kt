package pl.lazicki.seriessearch.features.searchSeries.data

import pl.lazicki.seriessearch.core.api.SeriesApi
import pl.lazicki.seriessearch.features.searchSeries.data.model.mapToDomain
import pl.lazicki.seriessearch.features.searchSeries.domain.SeriesRepository
import pl.lazicki.seriessearch.features.searchSeries.domain.model.Serie

class SeriesRepositoryImpl(
    private val api: SeriesApi
): SeriesRepository {

    override suspend fun searchSeries(query: String): List<Serie> =
        api.findSeries(query)
            .map { it.serie }
            .map { it.mapToDomain() }
}