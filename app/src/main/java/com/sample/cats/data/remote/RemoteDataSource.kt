package com.sample.cats.data.remote

import com.sample.cats.data.model.Cat
import kotlinx.coroutines.flow.Flow

interface RemoteDataSource {
    suspend fun getBreeds(): Flow<List<Cat>>
}