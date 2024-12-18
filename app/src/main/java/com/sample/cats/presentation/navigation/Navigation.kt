package com.sample.cats.presentation.navigation;

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.sample.cats.presentation.Screen
import com.sample.cats.presentation.features.SharedVM
import com.sample.cats.presentation.features.detail.DetailScreen
import com.sample.cats.presentation.features.main.MainScreen

@Composable
fun Navigation(navController: NavHostController) {
    val sharedVM = hiltViewModel<SharedVM>()
    NavHost(navController = navController, startDestination = Screen.MainScreen.route) {
        composable(route = Screen.MainScreen.route) {
            MainScreen(navController = navController, viewModel = sharedVM)
        }
        composable(
            route = "${Screen.DetailScreen.route}/{catBreedId}",
            arguments = listOf(navArgument("catBreedId") {
                type = NavType.StringType
                nullable = false
            })
        ) {
            val catBreedId = it.arguments?.getString("catBreedId")
            if (catBreedId != null) {
                DetailScreen(catBreedId, sharedVM)
            }
        }    }

}