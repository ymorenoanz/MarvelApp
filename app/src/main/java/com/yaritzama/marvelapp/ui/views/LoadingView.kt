package com.yaritzama.marvelapp.ui.views

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.yaritzama.marvelapp.ui.theme.TransparentBlack

@Composable
fun LoadingView(isLoading: Boolean){
    AnimatedVisibility(isLoading, enter = fadeIn(), exit = fadeOut()) {
        Box(modifier = Modifier.fillMaxSize().background(TransparentBlack), contentAlignment = Alignment.Center) {
            CircularProgressIndicator(modifier = Modifier.width(200.dp).height(200.dp), strokeWidth = 10.dp)
        }
    }
}
