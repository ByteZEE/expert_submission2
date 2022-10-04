package com.rafiadly.otakubandung.core.domain.repository

import com.rafiadly.otakubandung.core.domain.model.Anime
import kotlinx.coroutines.flow.Flow

interface IAnimeRepository {
    fun getSearchAnime(query: String): Flow<com.rafiadly.otakubandung.core.data.Resource<List<Anime>>>

    fun getDetailAnime(id: String): Flow<com.rafiadly.otakubandung.core.data.Resource<Anime>>

    fun getFavoriteAnime(): Flow<List<Anime>>

    fun isFavoriteAnime(id: String): Int

    suspend fun insertFavoriteAnime(anime: Anime)

    suspend fun deleteFavoriteAnime(anime: Anime): Int
}