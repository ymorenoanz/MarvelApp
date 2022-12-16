package com.yaritzama.marvelapp.presentation.ui.views.detail

import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import com.yaritzama.marvelapp.presentation.ui.views.common.BottomView
import com.yaritzama.marvelapp.presentation.ui.views.common.LoadingView


@Composable
fun DetailsView(url: String) {

    val isLoading: MutableState<Boolean> = remember { mutableStateOf(true) }

    Box {
        AndroidView(factory = {

            WebView(it).apply {
                webViewClient = object : WebViewClient(){
                    override fun onPageFinished(view: WebView?, url: String?) {
                        isLoading.value = false
                    }
                }
                loadUrl(url)
            }
        })
        BottomView()
    }


    LoadingView(isLoading = isLoading.value)

}