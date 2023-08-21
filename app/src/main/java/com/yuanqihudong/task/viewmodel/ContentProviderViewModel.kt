package com.yuanqihudong.task.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class ContentProviderViewModel : ViewModel() {

    val value: MutableLiveData<String> by lazy {
        MutableLiveData()
    }

    fun closeRun() {
        viewModelScope.launch {
            Log.i(this@ContentProviderViewModel::class.simpleName, "closeRun: start")
            delay(5000)
            value.postValue("123")
            Log.i(this@ContentProviderViewModel::class.simpleName, "closeRun: end")
        }
    }
}