package com.yuanqihudong.task.livedata.test

import androidx.lifecycle.MutableLiveData

class SimpleLiveData {

    private var name: MutableLiveData<String>? = null

    fun getName() = run {
        if (name == null) {
            name = MutableLiveData()
        }
        name
    }
}