package com.yuanqihudong.task.net

import com.yuanqihudong.task.bean.WanArticleBean
import retrofit2.Call
import retrofit2.http.GET

val ApiService = TaskClient.getService(Urls::class.java)

interface Urls {

    @GET("/article/list/0/json")
    fun getArticle(): Call<Any>

    @GET("/article/list/0/json")
    fun getArticleRetrofit(): Call<Any>

    @GET("/article/list/0/json")
    suspend fun getArticleSuspendRetrofit(): WanArticleBean

    @GET("/article/list/0/json")
    suspend fun getArticleNew(): ResponseBody<WanArticleBean.DataDTO>

    @GET("/article/list/0/js")
    suspend fun getArticleNew2(): ResponseBody<WanArticleBean.DataDTO>

}