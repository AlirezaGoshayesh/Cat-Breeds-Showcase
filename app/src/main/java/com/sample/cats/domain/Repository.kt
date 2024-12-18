package com.sample.cats.domain

import com.sample.cats.domain.model.CatBreed
import kotlinx.coroutines.flow.Flow

interface Repository {

    suspend fun getCatsBreeds(): Flow<List<CatBreed>>
    suspend fun saveFavorite(id: String)
    suspend fun removeFavorite(id: String)
}
