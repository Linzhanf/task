package com.yuanqihudong.task.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class ContentProviderViewModel : ViewModel() {

    val value : MutableLiveData<String> by lazy {
        MutableLiveData()
    }


}