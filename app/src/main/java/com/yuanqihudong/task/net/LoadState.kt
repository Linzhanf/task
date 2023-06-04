package com.yuanqihudong.task.net

import com.yuanqihudong.task.net.LoadStateConstant.Companion.LOAD_STATE_DEFAULT

sealed class LoadState {

    object Loading : LoadState()
    object Success : LoadState()
    data class Fail(val message: String, val type: Int = LOAD_STATE_DEFAULT) : LoadState()
}

class LoadStateConstant {

    companion object {
        const val LOAD_STATE_DEFAULT = 1
    }
}