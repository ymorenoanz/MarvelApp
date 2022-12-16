package com.yaritzama.marvelapp.presentation.navigation

sealed class Screens(val route: String){
    object CharactersView: Screens("characters")
    object SeriesView: Screens("series/")
    object ComicsView: Screens("comics/")
    object DetailsView: Screens("details/")
}