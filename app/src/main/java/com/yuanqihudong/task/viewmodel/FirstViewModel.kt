package com.yuanqihudong.task.viewmodel

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel

class FirstViewModel {

    private val _name = mutableStateOf("我的ViewModel")
    val name: State<String> = _name

    fun change(theName: String) {
        _name.value = theName
    }
}

class FirstCustomViewModel : ViewModel() {

    private val _name = mutableStateOf("继承的ViewModel")
    val name: State<String> = _name

    fun change(theName: String) {
        _name.value = theName
    }
}