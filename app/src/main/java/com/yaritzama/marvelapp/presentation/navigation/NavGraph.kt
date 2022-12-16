package com.yaritzama.marvelapp.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.yaritzama.marvelapp.presentation.ui.views.character.CharactersView
import com.yaritzama.marvelapp.presentation.ui.views.series.SeriesView
import com.yaritzama.marvelapp.presentation.ui.views.comics.ComicsView
import com.yaritzama.marvelapp.presentation.ui.views.detail.DetailsView

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

        composable(
            route = "${Screens.SeriesView.route}{characterId}",
            arguments = listOf(navArgument("characterId") { type = NavType.IntType })
        ) {  backStackEntry ->
            SeriesView(backStackEntry.arguments?.getInt("characterId"))
        }

        composable(
            route = "${Screens.ComicsView.route}{characterId}",
            arguments = listOf(navArgument("characterId") { type = NavType.IntType })
        ) { backStackEntry ->
            ComicsView( backStackEntry.arguments?.getInt("characterId"))
        }

        composable(route = "${Screens.DetailsView.route}{url}", arguments = listOf(navArgument("url"){
            type = NavType.StringType
        })) { backStackEntry ->
            DetailsView(backStackEntry.arguments?.getString("url") ?: "")
        }

    }
}