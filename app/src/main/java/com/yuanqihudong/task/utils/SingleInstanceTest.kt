package com.yuanqihudong.task.utils

import android.util.Log

// 单例模式
class SingleInstanceTest private constructor() {

    companion object {
        fun get() = run { Holder.instance }
    }

    private object Holder {
        val instance = SingleInstanceTest()
    }

    fun toastSingle(method: () -> Unit) {
        method()
    }
}

// 动态代理
interface Animal {
    fun back()
}

class Dog : Animal {
    override fun back() {
        Log.i("proxy", "back: this is a dog")
    }
}

class Zoo(animal: Animal) : Animal by animal

fun main() {
    Zoo(Dog()).back()
}

//operator
class User(var age: Int, var name: String) {
    operator fun component1() = age
    operator fun component2() = name
}

fun operatorMain() {
    val user = User(12, "name")
    val (age, name) = user
    println(age)
    println(name)
}

fun methodMain() {
    val user = User(12, "name")
    println(user.let { it.name = "123" })
    println(user.run { this.name = "123" })
}