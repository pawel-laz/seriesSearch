package pl.lazicki.seriessearch.features.searchSeries.data.model


import com.google.gson.annotations.SerializedName
import androidx.annotation.Keep
import pl.lazicki.seriessearch.features.searchSeries.domain.model.Serie

@Keep
data class SeriesDto(
    @SerializedName("name") val name: String,
    @SerializedName("genres") val genres: List<String>,
    @SerializedName("image") val image: ImageDto
)

fun SeriesDto.mapToDomain() =
    Serie(
        name,
        genres,
        image.medium?:image.original?:"placeholder"
    )