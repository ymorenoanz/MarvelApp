package com.yaritzama.marvelapp.ui.views

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.skydoves.landscapist.glide.GlideImage
import com.yaritzama.marvelapp.ui.MainViewModel

@Composable
fun ComicsView(navController: NavHostController, characterId: Int?) {

    //PENDING
    val vm: MainViewModel = hiltViewModel()
    //val result = vm.getComicList(characterId)
    val comic = vm.comicList
    vm.setCharacterId(characterId?:0)
    vm.getComicList()

    Column(modifier = Modifier.padding(8.dp)) {
        LazyVerticalGrid(
            columns = GridCells.Fixed(1),
            content = {
                items(comic) { item->
                    Card(elevation = 8.dp, modifier =
                    Modifier.padding(8.dp)){
                        Column(
                            Modifier
                                .padding(16.dp)
                                .align(Alignment.CenterHorizontally)) {
                            Text(
                                modifier = Modifier.fillMaxWidth(),
                                text = item.name.toString(),
                                fontWeight = FontWeight.Bold,
                                textAlign = TextAlign.Left
                            )

                            Box(modifier = Modifier
                                .height(200.dp)
                                .width(200.dp)){
                                val url = item.pathImage.toString()
                                val correctUrl = url.removePrefix("http")
                                GlideImage(imageModel = { "https" + correctUrl +
                                        "."+  item.extensionImage.toString() })
                            }
                        }

                    }
                }
            })
    }
}