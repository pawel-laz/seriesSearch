package pl.lazicki.seriessearch.core.api

import pl.lazicki.seriessearch.features.searchSeries.data.model.SeriesResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface SeriesApi {

    @GET("search/shows")
    suspend fun findSeries(@Query("q") query: String): List<SeriesResponse>

}