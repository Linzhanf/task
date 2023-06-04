package com.yuanqihudong.task.net

data class ResponseBody<T>(
        val errorCode: Int,
        val errorMessage: String,
        val data: T
)