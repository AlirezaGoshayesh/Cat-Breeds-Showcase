package com.sample.cats.data.remote

import com.sample.cats.data.model.Cat
import com.sample.cats.data.remote.connection.MService
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class RemoteDataSourceImpl @Inject constructor(private val service: MService) : RemoteDataSource {
    override suspend fun getBreeds(): Flow<List<Cat>> {
        return flow {
            emit(service.getBreeds())
        }
    }
}