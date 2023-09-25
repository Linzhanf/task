package com.yuanqihudong.task.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.yuanqihudong.task.bean.WanArticleBean
import com.yuanqihudong.task.net.ApiService
import com.yuanqihudong.task.net.LoadState
import com.yuanqihudong.task.net.TaskClient
import com.yuanqihudong.task.net.Urls
import kotlinx.coroutines.delay

class CoroutineVm : BaseViewModel() {

    val articleLoadState = MutableLiveData<LoadState>()
    val articleResult = MutableLiveData<WanArticleBean.DataDTO>()

    val articleResultWithResult = MutableLiveData<WanArticleBean.DataDTO>()

    fun syncRequest() = run {
        launch({
            articleResult.value = processData(ApiService.getArticleNew())
        }, {
            articleLoadState.value = LoadState.Fail(it.message ?: "加载失败", 0)
        })
    }

    fun syncRequestWithResult() = run {
        launch({
            articleResultWithResult.value = processData(ApiService.getArticleNew2())
        }, {
            articleLoadState.value = LoadState.Fail(it.message ?: "加载失败", 1)
        })
    }
}