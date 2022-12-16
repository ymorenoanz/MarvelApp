package com.yaritzama.marvelapp.presentation.ui.views.character

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import com.yaritzama.marvelapp.R
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.skydoves.landscapist.glide.GlideImage
import com.yaritzama.marvelapp.domain.model.CharacterModel
import com.yaritzama.marvelapp.presentation.ui.theme.LayerColor

@Composable
fun ItemCharacterView(
    item: CharacterModel,
    onClickSeries: () -> Unit,
    onClickComics: () -> Unit,
    onClickDetails: () -> Unit
) {
    Card(
        elevation = 8.dp, modifier =
        Modifier.padding(8.dp), shape = RoundedCornerShape(20.dp)
    ) {
        Column(

        ) {

            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(300.dp)
            ) {

                GlideImage(imageModel = { item.imageUrl.toString()}, failure = {R.drawable.no_image })
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(LayerColor)
                ) {

                    Column(modifier = Modifier
                        .padding(10.dp)
                        .fillMaxSize()) {
                        Text(
                            modifier = Modifier.fillMaxWidth(),
                            text = item.name.toString(),
                            fontWeight = FontWeight.Bold,
                            textAlign = TextAlign.Left, color = Color.White,
                            style = TextStyle(
                                fontSize = 25.sp
                            )
                        )

                        Text(
                            modifier = Modifier
                                .fillMaxWidth(),
                            text = item.description.ifEmpty { "No Description" },
                            fontWeight = FontWeight.Light,
                            textAlign = TextAlign.Left, color = Color.White,
                            maxLines = 4,
                            overflow = TextOverflow.Ellipsis
                        )

                        Column(modifier = Modifier.fillMaxSize(), verticalArrangement = Arrangement.Bottom) {
                            Row( modifier = Modifier
                                .fillMaxWidth()
                                .height(40.dp), horizontalArrangement = Arrangement.SpaceBetween) {
                                Box(modifier = Modifier
                                    .width(100.dp)
                                    .fillMaxHeight()
                                    .clickable { onClickSeries() }, contentAlignment = Alignment.Center) {
                                    Text(stringResource(id =R.string.series),color = Color.White)
                                }

                                Spacer(modifier = Modifier
                                    .fillMaxHeight()
                                    .width(1.dp)
                                    .background(Color.White))

                                Box(modifier = Modifier
                                    .width(100.dp)
                                    .fillMaxHeight()
                                    .clickable { onClickComics() }, contentAlignment = Alignment.Center) {

                                    Text(stringResource(id =R.string.comics),color = Color.White)

                                }
                                Spacer(modifier = Modifier
                                    .fillMaxHeight()
                                    .width(1.dp)
                                    .background(Color.White))
                                Box(modifier = Modifier
                                    .width(100.dp)
                                    .fillMaxHeight()
                                    .clickable { onClickDetails() }, contentAlignment = Alignment.Center) {
                                    Text(stringResource(id =R.string.detail),color = Color.White)
                                }
                            }
                        }


                    }


                }
            }

        }

    }
}

