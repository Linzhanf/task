package com.yuanqihudong.task.net

import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.http.GET

interface Urls {

    @GET("/article/list/0/json")
    fun getArticle(): Call<Any>

    @GET("/article/list/0/json")
    fun getArticleRetrofit(): Call<Any>
}