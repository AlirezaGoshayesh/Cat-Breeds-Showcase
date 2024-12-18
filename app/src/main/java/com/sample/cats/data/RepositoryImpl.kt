package com.sample.cats.data

import com.sample.cats.data.local.LocalDataSource
import com.sample.cats.data.remote.RemoteDataSource
import com.sample.cats.domain.Repository
import com.sample.cats.domain.model.CatBreed
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class RepositoryImpl @Inject constructor(
    private val remoteDataSource: RemoteDataSource,
    private val localDataSource: LocalDataSource
) : Repository {

    override suspend fun getCatsBreeds(): Flow<List<CatBreed>> {
        val favoriteIdsFlow = localDataSource.getFavoriteIds()
        val remoteCatsFlow = remoteDataSource.getBreeds()

        return combine(remoteCatsFlow, favoriteIdsFlow) { remoteCats, favoriteIds ->
            remoteCats.map { cat ->
                CatBreed(
                    description = cat.description,
                    id = cat.id,
                    name = cat.name,
                    image = cat.image?.url,
                    lifeSpan = cat.life_span,
                    origin = cat.origin,
                    temperament = cat.temperament,
                    isFavorite = favoriteIds.contains(cat.id)
                )
            }
        }
    }

    override suspend fun saveFavorite(id: String) {
        localDataSource.saveFavorite(id)
    }

    override suspend fun removeFavorite(id: String) {
        localDataSource.removeFavorite(id)
    }
}