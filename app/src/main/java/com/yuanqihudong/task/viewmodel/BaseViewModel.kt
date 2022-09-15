package com.yuanqihudong.task.viewmodel

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.CoroutineExceptionHandler

open class BaseViewModel : ViewModel() {

    //向协程提供一个全局异常，用来处理异常UI
    fun <T> errorContext(err: (errorMessage: Throwable) -> Unit): CoroutineExceptionHandler {
        return CoroutineExceptionHandler { _, e ->
            err.invoke(e)
        }
    }
}