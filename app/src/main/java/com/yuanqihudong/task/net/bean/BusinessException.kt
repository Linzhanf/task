package com.yuanqihudong.task.net.bean

data class BusinessException(val errorCode: Int, val errorMessage: String) : RuntimeException()