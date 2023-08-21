package com.yuanqihudong.task.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.yuanqihudong.task.net.bean.ResponseBody
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

fun ViewModel.launch(
        block: suspend CoroutineScope.() -> Unit,
        onError: (e: Throwable) -> Unit = {},
        onComplete: () -> Unit = {}
) {
    viewModelScope.launch(SupervisorJob() + CoroutineExceptionHandler { _, e -> onError(e) }) {
        try {
            withContext(Dispatchers.IO) {
                block.invoke(this)
            }
        } finally {
            onComplete()
        }
    }
}

fun <T> processData(responseBody: ResponseBody<T>): T {
    return if (responseBody.errorCode == 0) responseBody.data else throw Throwable(responseBody.errorMessage)
}