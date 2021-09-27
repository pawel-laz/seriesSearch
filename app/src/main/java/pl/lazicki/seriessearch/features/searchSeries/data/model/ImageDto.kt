package pl.lazicki.seriessearch.features.searchSeries.data.model


import com.google.gson.annotations.SerializedName
import androidx.annotation.Keep

@Keep
data class ImageDto(
    @SerializedName("medium")
    val medium: String,
    @SerializedName("original")
    val original: String
)