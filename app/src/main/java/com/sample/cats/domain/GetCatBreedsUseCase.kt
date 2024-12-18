package com.sample.cats.domain;

import com.sample.cats.domain.base.UseCase
import com.sample.cats.domain.exceptions.IErrorHandler
import com.sample.cats.domain.model.CatBreed
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetCatBreedsUseCase @Inject constructor(
    private val repository: Repository,
    errorHandler: IErrorHandler
) : UseCase<Unit, Flow<List<CatBreed>>>(errorHandler) {
    override suspend fun execute(parameters: Unit): Flow<List<CatBreed>> {
        return repository.getCatsBreeds()
    }
}