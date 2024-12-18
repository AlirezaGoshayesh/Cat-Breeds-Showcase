package com.sample.cats.presentation.features.main

import android.net.Uri
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.sample.cats.domain.base.Resource
import com.sample.cats.domain.model.CatBreed
import com.sample.cats.presentation.ErrorMessage
import com.sample.cats.presentation.LoadingDialog
import com.sample.cats.presentation.Screen
import com.sample.cats.presentation.features.SharedVM

@Composable
fun MainScreen(
    navController: NavHostController,
    viewModel: SharedVM = hiltViewModel()
) {
    val catListRes by viewModel.catListRes

    when (catListRes) {
        is Resource.Error -> {
            ErrorMessage(
                modifier = Modifier.fillMaxSize(),
                message = (catListRes as Resource.Error).errorModel.getErrorMessage(),
                onClickRetry = { viewModel.getList() })
        }

        is Resource.Loading -> LoadingDialog()
        is Resource.Success -> {
            val catList = (catListRes as Resource.Success<List<CatBreed>>).data
            LazyColumn(
                modifier = Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.Top,
                contentPadding = PaddingValues(4.dp)
            ) {
                items(catList.size) { index ->
                    CatBreedItem(
                        modifier = Modifier,
                        url = catList[index].image.toString(),
                        name = catList[index].name,
                        isFavorite = catList[index].isFavorite,
                        onFavoriteClick = {
                            viewModel.setFavoriteStatus(
                                catList[index].id,
                                catList[index].isFavorite
                            )
                        }, onItemClick = {
                            navController.navigate("${Screen.DetailScreen.route}/${catList[index].id}")
                        }
                    )
                }
            }
        }

        else -> {}
    }

}

