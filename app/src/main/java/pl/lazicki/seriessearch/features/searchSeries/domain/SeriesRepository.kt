package pl.lazicki.seriessearch.features.searchSeries.domain

import pl.lazicki.seriessearch.features.searchSeries.domain.model.Serie

interface SeriesRepository {

    suspend fun searchSeries(query: String): List<Serie>
}