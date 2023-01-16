package com.yaritzama.marvelapp.presentation.ui.views.character

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.google.accompanist.swiperefresh.SwipeRefresh
import com.google.accompanist.swiperefresh.rememberSwipeRefreshState
import com.yaritzama.marvelapp.core.utils.gesturesDisabled
import com.yaritzama.marvelapp.presentation.navigation.Screens
import com.yaritzama.marvelapp.presentation.ui.theme.BackGroundColor
import com.yaritzama.marvelapp.presentation.ui.views.common.BottomView
import com.yaritzama.marvelapp.presentation.viewmodel.MainViewModel
import com.yaritzama.marvelapp.ui.views.AlertView
import com.yaritzama.marvelapp.presentation.ui.views.common.LoadingView
import org.koin.androidx.compose.getKoin
import org.koin.androidx.compose.inject
import org.koin.androidx.compose.koinViewModel

@Composable
fun CharactersView(navController: NavHostController) {

    val viewModel = koinViewModel<MainViewModel>()

    val uiState = viewModel.stateUI.value
    val showAlert = uiState.error != null

    val refreshState by remember {
        mutableStateOf(false)
    }

    SwipeRefresh(state = rememberSwipeRefreshState(
        isRefreshing = refreshState,
    ),
        onRefresh = {
            viewModel.getCharacterList(needsUpdate = true)
        }) {
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
                    modifier = Modifier.padding(top = 30.dp, start = 10.dp, end = 10.dp, bottom = 30.dp),
                    columns = GridCells.Fixed(1),
                    content = {
                        items(uiState.data ?: emptyList()) { item ->
                            ItemCharacterView(
                                item = item,
                                onClickSeries = {
                                    navController.navigate("${Screens.SeriesView.route}${item.id}")
                                },
                                onClickComics = {
                                    navController.navigate("${Screens.ComicsView.route}${item.id}")
                                },
                                onClickDetails = {
                                    navController.navigate("${Screens.DetailsView.route}${item.detailUrl}")
                                }
                            )
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