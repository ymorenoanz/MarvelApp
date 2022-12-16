package com.yaritzama.marvelapp.ui.views.comics

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.yaritzama.marvelapp.ui.viewmodel.ComicsViewModel

@Composable
fun ComicsView(navController: NavHostController, characterId: Int?) {

    val vm: ComicsViewModel = hiltViewModel()
    val comic = vm.comicList
    vm.setCharacterId(characterId?:0)

    Column(modifier = Modifier.padding(8.dp)) {
        LazyVerticalGrid(
            columns = GridCells.Fixed(1),
            content = {
                items(comic) { item->
                    ItemComic(comicModel = item)
                }
            })
    }
}