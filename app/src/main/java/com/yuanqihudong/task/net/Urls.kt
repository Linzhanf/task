package com.yuanqihudong.task.net

import com.yuanqihudong.task.bean.WanArticleBean
import retrofit2.Call
import retrofit2.http.GET

interface Urls {

    @GET("/article/list/0/json")
    fun getArticle(): Call<Any>

    @GET("/article/list/0/json")
    fun getArticleRetrofit(): Call<Any>

    @GET("/article/list/0/json")
    suspend fun getArticleSuspendRetrofit(): WanArticleBean

}