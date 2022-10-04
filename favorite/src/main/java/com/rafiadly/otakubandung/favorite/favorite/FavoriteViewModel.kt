package com.rafiadly.otakubandung.favorite.favorite

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.rafiadly.otakubandung.core.domain.usecase.AnimeUseCase

class FavoriteViewModel(animeUseCase: AnimeUseCase) : ViewModel() {
    val favoriteAnime = animeUseCase.getFavoriteAnime().asLiveData()
}