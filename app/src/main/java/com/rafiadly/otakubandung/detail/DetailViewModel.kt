package com.rafiadly.otakubandung.detail

import androidx.lifecycle.*
import com.rafiadly.otakubandung.core.domain.model.Anime
import com.rafiadly.otakubandung.core.domain.usecase.AnimeUseCase
import kotlinx.coroutines.launch

class DetailViewModel(private val animeUseCase: AnimeUseCase) : ViewModel() {
    private val animeId = MutableLiveData<String>()
    val animeDetail = Transformations.switchMap(animeId) {
        animeUseCase.getDetailAnime(it).asLiveData()
    }
    private val _isFavorite = MutableLiveData<Int>()
    var isFavorite: LiveData<Int> = _isFavorite


    fun setId(id: String) {
        if (animeId.value == id) return
        animeId.value = id
    }

    fun isFavoriteAnime(id: String?) {
        _isFavorite.postValue(id?.let { animeUseCase.isFavoriteAnime(it) })
    }

    fun insertFavoriteAnime(anime: Anime) =
        viewModelScope.launch { animeUseCase.insertFavoriteAnime(anime) }

    fun deleteFavoriteAnime(anime: Anime) =
        viewModelScope.launch { animeUseCase.deleteFavoriteAnime(anime) }

}