package com.yaritzama.marvelapp.ui.views

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material.Button
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.skydoves.landscapist.glide.GlideImage
import com.yaritzama.marvelapp.ui.MainViewModel

@Composable
fun CharactersView() {

    val vm: MainViewModel = viewModel()
    val character = vm.characterList

    Column(modifier = Modifier.padding(8.dp)) {
        LazyVerticalGrid(
            columns = GridCells.Fixed(1),
            content = {
                items(character) { item->
                    Card(elevation = 8.dp, modifier =
                    Modifier.padding(8.dp)){
                        Column(
                            Modifier.padding(16.dp)
                                .align(Alignment.CenterHorizontally)) {
                            Text(
                                modifier = Modifier.fillMaxWidth(),
                                text = item.name.toString(),
                                fontWeight = FontWeight.Bold,
                                textAlign = TextAlign.Left
                            )
                            Box(modifier = Modifier
                                .height(100.dp)
                                .width(200.dp)){
                                val url = item.imageUrl.toString()
                                val correctUrl = url.removePrefix("http")
                                GlideImage(imageModel = { "https" + correctUrl +
                                         "."+  item.extensionImage.toString() })
                            }

                            Row(){
                                Row(modifier = Modifier.width(90.dp)) {
                                    Button(onClick = { /*TODO*/ } ) {
                                        Text("Series")
                                    }
                                }

                                Row(modifier = Modifier.width(100.dp)) {
                                    Button(onClick = { /*TODO*/ }) {
                                        Text("Comics")
                                    }
                                }

                                Row(modifier = Modifier.width(100.dp)) {
                                    Button(onClick = { /*TODO*/ }) {
                                        Text("Details")
                                    }
                                }
                            }
                        }

                    }
                }
            })


    }
}