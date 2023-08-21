package com.yuanqihudong.task.net.bean

import java.io.Serializable

data class ServiceResult<T>(
    var errorCode: Int = -1,
    var errorMessage: String = "",
    var data: T? = null
) : Serializable