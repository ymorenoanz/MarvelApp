package com.yaritzama.marvelapp.presentation.ui.state


data class StateUI<T>(
    val data: T? = null,
    val isLoading: Boolean = false,
    val error: String? = null
)