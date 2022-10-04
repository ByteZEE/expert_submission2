package com.rafiadly.otakubandung.favorite.di

import com.rafiadly.otakubandung.favorite.favorite.FavoriteViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val favoriteModule = module {
    viewModel { FavoriteViewModel(get()) }
}