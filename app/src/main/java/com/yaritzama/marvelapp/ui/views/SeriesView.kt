package com.yaritzama.marvelapp.ui.views

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.yaritzama.marvelapp.ui.viewmodel.SeriesViewModel

@Composable
fun SeriesView(navController: NavHostController, characterId: Int?){
    val seriesViewModel: SeriesViewModel = hiltViewModel()
    seriesViewModel.setCharacterId(characterId ?: 0)
    Text("Series Marvel")
    LazyColumn{
        items(seriesViewModel.seriesList){
            item -> Text(text = "${item.name}")
        }
    }
}