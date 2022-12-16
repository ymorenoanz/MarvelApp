package com.yaritzama.marvelapp.ui.views.detail

import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.compose.runtime.*
import androidx.compose.ui.viewinterop.AndroidView
import com.yaritzama.marvelapp.ui.views.LoadingView


@Composable
fun DetailsView(url: String) {

    val isLoading: MutableState<Boolean> = remember { mutableStateOf(true) }

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

    LoadingView(isLoading = isLoading.value)

}