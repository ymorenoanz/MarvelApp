package com.yaritzama.marvelapp.ui.views.comics

import androidx.compose.foundation.layout.*
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.yaritzama.marvelapp.domain.model.ComicModel

@Composable
fun ItemComic(comicModel: ComicModel) {
    Card(
        elevation = 8.dp, modifier =
        Modifier.padding(8.dp)
    ) {
        Column(
            Modifier
                .padding(16.dp), horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                modifier = Modifier.fillMaxWidth(),
                text = comicModel.name.toString(),
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Left
            )

            Box(
                modifier = Modifier
                    .height(200.dp)
                    .width(200.dp)
            ) {
                /*val url = item.pathImage.toString()
                val correctUrl = url.removePrefix("http")
                GlideImage(imageModel = { "https" + correctUrl +
                        "."+  item.extensionImage.toString() })*/
            }
        }

    }
}

