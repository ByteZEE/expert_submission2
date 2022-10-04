package com.rafiadly.otakubandung.di

import com.rafiadly.otakubandung.core.domain.usecase.AnimeInteractor
import com.rafiadly.otakubandung.core.domain.usecase.AnimeUseCase
import com.rafiadly.otakubandung.detail.DetailViewModel
import com.rafiadly.otakubandung.discover.DiscoverViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val useCaseModule = module {
    factory<AnimeUseCase> { AnimeInteractor(get()) }
}

val viewModelModule = module {
    viewModel { DiscoverViewModel(get()) }
    viewModel { DetailViewModel(get()) }
}