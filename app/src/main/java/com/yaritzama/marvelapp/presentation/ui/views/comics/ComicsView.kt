package com.yaritzama.marvelapp.presentation.ui.views.comics

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.yaritzama.marvelapp.core.utils.gesturesDisabled
import com.yaritzama.marvelapp.presentation.ui.theme.BackGroundColor
import com.yaritzama.marvelapp.presentation.ui.views.common.BottomView
import com.yaritzama.marvelapp.presentation.viewmodel.ComicsViewModel
import com.yaritzama.marvelapp.ui.views.AlertView
import com.yaritzama.marvelapp.presentation.ui.views.common.LoadingView
import org.koin.androidx.compose.koinViewModel

@Composable
fun ComicsView( characterId: Int?) {

    val viewModel = koinViewModel<ComicsViewModel>()
    val uiState = viewModel.stateUI.value
    val showAlert = uiState.error != null

    viewModel.setCharacterId(characterId?:0)

    Box() {
        Box(
            modifier = Modifier.background(
                BackGroundColor
            ),
        ) {
            Column(
                modifier = Modifier
                    .gesturesDisabled(disabled = uiState.isLoading)
            ) {
                LazyVerticalGrid(
                    modifier = Modifier.padding(top = 30.dp, start = 10.dp, end = 10.dp, bottom = 40.dp),
                    columns = GridCells.Fixed(1),
                    content = {
                        items(uiState.data ?: emptyList()) { item ->
                            ItemComicView(item)
                        }
                    })


            }

           BottomView()

        }



        AlertView(openDialog = showAlert, message = uiState.error) {
            viewModel.setErrorNull()
        }
        LoadingView(isLoading = uiState.isLoading)
    }
}