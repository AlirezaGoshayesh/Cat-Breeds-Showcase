package com.sample.cats.data.local

import kotlinx.coroutines.flow.Flow

interface LocalDataSource {
    suspend fun saveFavorite(id: String)
    suspend fun removeFavorite(id: String)
    fun getFavoriteIds(): Flow<Set<String>>
}