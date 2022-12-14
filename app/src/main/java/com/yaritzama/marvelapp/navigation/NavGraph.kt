package com.yaritzama.marvelapp.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.yaritzama.marvelapp.ui.views.CharactersView
import com.yaritzama.marvelapp.ui.views.ComicsView
import com.yaritzama.marvelapp.ui.views.DetailsView
import com.yaritzama.marvelapp.ui.views.SeriesView

@Composable
fun NavGraph(navController: NavHostController) {
    NavHost(
        navController = navController,
        startDestination = Screens.CharactersView.route
    ) {

        composable(
            route = Screens.CharactersView.route
        ) {
            CharactersView(navController)
        }

        composable(route = Screens.SeriesView.route) {
            SeriesView(navController)
        }

        composable(
            route = "${Screens.ComicsView.route}{characterId}",
            arguments = listOf(navArgument("characterId") { type = NavType.IntType })
        ) { backStackEntry ->
            ComicsView(navController, backStackEntry.arguments?.getInt("characterId"))
        }

        composable(route = Screens.DetailsView.route) {
            DetailsView(navController)
        }

    }
}