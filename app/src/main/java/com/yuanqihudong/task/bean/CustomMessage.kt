package com.yuanqihudong.task.bean

import androidx.appcompat.app.AppCompatActivity
import kotlin.reflect.KClass

data class CustomMessage(
    val name: String?,
    val clazz: KClass<out AppCompatActivity>
)
