package com.yaritzama.marvelapp.utils

import com.yaritzama.marvelapp.domain.model.BaseResult
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext

suspend fun <T> BaseResult<T>.validateResult(dispatcher: CoroutineDispatcher, onSuccess: (T) -> Unit, onError:(message: String) -> Unit ){
    val result = this
    withContext(dispatcher){
        when(result){
            is BaseResult.Success -> onSuccess(result.data)
            is BaseResult.Error -> onError(result.message)
        }
    }
}

suspend fun <T> BaseResult<T>.validateResult(dispatcher: CoroutineDispatcher, result:(data: T?, error: String?) -> Unit ){
    val baseResult = this
    withContext(dispatcher){
        when(baseResult){
            is BaseResult.Success -> result(baseResult.data ,null)
            is BaseResult.Error -> result(null,baseResult.message)
        }
    }
}