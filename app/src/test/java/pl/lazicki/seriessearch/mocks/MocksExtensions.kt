package pl.lazicki.seriessearch.mocks

import pl.lazicki.seriessearch.features.searchSeries.data.model.SeriesDto
import pl.lazicki.seriessearch.features.searchSeries.domain.model.Serie
import pl.lazicki.seriessearch.features.searchSeries.presentation.model.SerieDisplayable

fun SeriesDto.Companion.mock() =
    SeriesDto(
        name = "Show Title",
        genres = listOf("genre", "genre", "genre"),
        null
    )

fun Serie.Companion.mock() =
    Serie(
        name = "Show Title",
        genres = listOf("genre", "genre", "genre"),
        null
    )

fun SerieDisplayable.Companion.mock() =
    SerieDisplayable(
        name = "Show Title",
        genres = listOf("genre", "genre", "genre"),
        null
    )
