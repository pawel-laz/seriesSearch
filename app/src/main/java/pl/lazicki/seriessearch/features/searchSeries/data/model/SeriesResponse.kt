package pl.lazicki.seriessearch.features.searchSeries.data.model

import androidx.annotation.Keep
import com.google.gson.annotations.SerializedName

@Keep
data class SeriesResponse(
    @SerializedName("score") val score: Double,
    @SerializedName("show") val serie: SeriesDto
)
