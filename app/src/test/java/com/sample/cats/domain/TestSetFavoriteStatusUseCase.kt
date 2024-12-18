package com.sample.cats.domain

import com.sample.cats.domain.exceptions.IErrorHandler
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Test

class SetFavoriteStatusUseCaseTest {

    private lateinit var setFavoriteStatusUseCase: SetFavoriteStatusUseCase
    private val repository: Repository = mockk()
    private val errorHandler: IErrorHandler = mockk()

    @Before
    fun setUp() {
        setFavoriteStatusUseCase = SetFavoriteStatusUseCase(repository, errorHandler)
    }

    @Test
    fun `should save favorite when favorite is true`() = runTest {

        val catBreedId = "abys"
        val isFavorite = true

        coEvery { repository.saveFavorite(catBreedId) } returns Unit

        setFavoriteStatusUseCase(Pair(catBreedId, isFavorite))

        coVerify { repository.saveFavorite(catBreedId) }
    }

    @Test
    fun `should remove favorite when favorite is false`() = runTest {
        val catBreedId = "pers"
        val isFavorite = false

        coEvery { repository.removeFavorite(catBreedId) } returns Unit

        setFavoriteStatusUseCase(Pair(catBreedId, isFavorite))

        coVerify { repository.removeFavorite(catBreedId) }
    }
}
