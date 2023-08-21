package com.yuanqihudong.task.viewmodel

import androidx.lifecycle.MutableLiveData
import com.yuanqihudong.task.bean.WanArticleBean
import com.yuanqihudong.task.net.retrofit.ApiService
import com.yuanqihudong.task.net.LoadState

class CoroutineVm : BaseViewModel() {

    val articleLoadState = MutableLiveData<LoadState>()
    val articleResult = MutableLiveData<WanArticleBean.DataDTO>()

    val articleResultWithResult = MutableLiveData<WanArticleBean.DataDTO>()

    fun syncRequest() = run {
        launch({
            articleResult.postValue(processData(ApiService.getArticleNew()))
        }, {
            articleLoadState.postValue(LoadState.Fail(it.message ?: "加载失败", 0))
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