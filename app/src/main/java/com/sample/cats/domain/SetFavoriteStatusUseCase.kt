package com.sample.cats.domain;

import com.sample.cats.domain.base.UseCase
import com.sample.cats.domain.exceptions.IErrorHandler
import com.sample.cats.domain.model.CatBreed
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class SetFavoriteStatusUseCase @Inject constructor(
    private val repository: Repository,
    errorHandler: IErrorHandler
) : UseCase<Pair<String, Boolean>, Unit>(errorHandler) {
    override suspend fun execute(parameters: Pair<String, Boolean>) {
        return if (parameters.second)
            repository.saveFavorite(id = parameters.first)
        else
            repository.removeFavorite(parameters.first)
    }
}
