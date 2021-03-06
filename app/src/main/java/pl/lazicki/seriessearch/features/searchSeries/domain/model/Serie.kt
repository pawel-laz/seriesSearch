package pl.lazicki.seriessearch.features.searchSeries.domain.model

import pl.lazicki.seriessearch.features.searchSeries.presentation.model.SerieDisplayable

data class Serie(
    val name: String,
    val genres: List<String>,
    val image: String?
) {
    companion object
}

fun Serie.mapToPresentation() = SerieDisplayable(
    name,
    genres,
    image
)