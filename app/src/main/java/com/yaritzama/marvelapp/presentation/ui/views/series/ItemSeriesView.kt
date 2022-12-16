package com.yaritzama.marvelapp.presentation.ui.views.series

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.yaritzama.marvelapp.domain.model.ComicModel
import com.yaritzama.marvelapp.domain.model.SeriesModel
import com.yaritzama.marvelapp.presentation.ui.theme.BackGroundCardColor
import com.yaritzama.marvelapp.presentation.viewmodel.SeriesViewModel

@Composable
fun ItemSeriesView(item: SeriesModel) {
    Card(
        elevation = 8.dp, modifier =
        Modifier.padding(8.dp).height(80.dp), backgroundColor = BackGroundCardColor
    ) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            Box(modifier = Modifier
                .padding(start = 10.dp)
                .height(55.dp)
                .width(55.dp)
                .aspectRatio(1f)
                .background(Brush.linearGradient(listOf(Color.Transparent, Color.Black)), shape = CircleShape),
                contentAlignment = Alignment.Center
            ) {
                Text(text = item.name?.first().toString(), color= Color.White,   textAlign = TextAlign.Center)
            }
            Column(
                Modifier
                    .padding(16.dp), horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    modifier = Modifier.fillMaxWidth(),
                    text = item.name.toString(),
                    fontWeight = FontWeight.Bold,
                    textAlign = TextAlign.Left,
                    style = TextStyle(
                        color = Color.White
                    )
                )
            }
        }


    }
}