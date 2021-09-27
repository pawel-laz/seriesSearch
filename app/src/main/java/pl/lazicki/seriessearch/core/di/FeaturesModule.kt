package pl.lazicki.seriessearch.core.di

import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import pl.lazicki.seriessearch.features.searchSeries.presentation.SeriesViewModel
import pl.lazicki.seriessearch.features.searchSeries.data.SeriesRepositoryImpl
import pl.lazicki.seriessearch.features.searchSeries.domain.SearchUseCase
import pl.lazicki.seriessearch.features.searchSeries.domain.SeriesRepository

val featuresModule = module {
    factory<SeriesRepository> { SeriesRepositoryImpl(get()) }

    factory { SearchUseCase(get()) }

    viewModel { SeriesViewModel(get()) }
}