package com.sample.cats.presentation.features.detail

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import com.sample.cats.presentation.features.SharedVM

@Composable
fun DetailScreen(
    catBreedId: String,
    viewModel: SharedVM = hiltViewModel()
) {
    val catBreed = viewModel.getCatBreedById(catBreedId)!!
    CatBreedDetail(
        modifier = Modifier.fillMaxSize(),
        description = catBreed.description,
        image = catBreed.image.toString(),
        lifeSpan = catBreed.lifeSpan,
        name = catBreed.name,
        origin = catBreed.origin,
        temperament = catBreed.temperament,
        isFavorite = catBreed.isFavorite,
        onFavoriteClick = {
            viewModel.setFavoriteStatus(catBreed.id, catBreed.isFavorite)
        }
    )
}

