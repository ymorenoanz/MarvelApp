package com.yaritzama.marvelapp.navigation

sealed class Screens(val route: String){
    object CharactersView: Screens("characters/{characterId}")
    object SeriesView: Screens("series")
    object ComicsView: Screens("comics")
    object DetailsView: Screens("details")
}