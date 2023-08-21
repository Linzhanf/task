package com.yuanqihudong.task.net.retrofit

import com.yuanqihudong.task.bean.WanArticleBean
import com.yuanqihudong.task.net.bean.ResponseBody
import com.yuanqihudong.task.net.bean.ServiceResult
import io.reactivex.rxjava3.core.Observable
import retrofit2.Call
import retrofit2.http.FieldMap
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.POST

val ApiService = TaskClient.getService(Urls::class.java)

interface Urls {

    @POST("/article/list/0/json")
    @FormUrlEncoded
    fun getArticle(@FieldMap map: HashMap<String, String>): Call<Any>

    @GET("/article/list/0/json")
    fun getArticleRetrofit(): Call<Any>

    @GET("/article/list/0/json")
    suspend fun getArticleSuspendRetrofit(): WanArticleBean

    @GET("/article/list/0/json")
    suspend fun getArticleNew(): ResponseBody<WanArticleBean.DataDTO>

    @GET("/article/list/0/js")
    suspend fun getArticleNew2(): ResponseBody<WanArticleBean.DataDTO>

    @GET("/article/list/0/json")
    fun getArticleRx(): Observable<ServiceResult<WanArticleBean.DataDTO>>

}