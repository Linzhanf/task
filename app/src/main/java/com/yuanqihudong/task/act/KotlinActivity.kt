package com.yuanqihudong.task.act

import android.os.AsyncTask
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import com.yuanqihudong.task.base.BaseActivity
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.async
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.withContext
import java.net.URL
import kotlin.concurrent.thread
import kotlin.coroutines.Continuation
import kotlin.coroutines.CoroutineContext
import kotlin.coroutines.EmptyCoroutineContext
import kotlin.coroutines.createCoroutine
import kotlin.coroutines.resume
import kotlin.coroutines.suspendCoroutine

class KotlinActivity : BaseActivity() {

    var marks: Int = 1
        get() = field * 9
        set(value) {
            field = value + 1
        }

    var listReduce = listOf(1, 2, 3).reduce { sum, element -> sum.times(element) }
    var listFold = listOf(1, 2, 3).fold(10) { sum, element -> sum + element }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyContentView()
        }
    }

    @Composable
    fun MyContentView() {
        val user = User(marks, "li lei")
        Column {
            Text(text = "listFold=$listFold, listReduce=$listReduce")
            Text(text = "normal, name: ${user.name}, age: ${user.age}")
            Text(text = "let: ${
                user.let {
                    it.age = 100
                    "this is a back"
                }
            }, name: ${user.name}, age: ${user.age}")
            Text(text = "run: ${
                user.run {
                    age = 200
                    "this is a back"
                }
            }, name: ${user.name}, age: ${user.age}")
            Text(text = "also: ${user.also { it.age = 300 }}, name: ${user.name}, age: ${user.age}")
            Text(text = "apply: ${user.apply { age = 400 }}, name: ${user.name}, age: ${user.age}")
            Text(text = "with: ${
                with(user) {
                    age = 500
                }
            }, name: ${user.name}, age: ${user.age}")
            Button(onClick = { runBlockingAbout() }) {
                Text(text = "runBlocking")
            }
            Button(onClick = { operateList() }) {
                Text(text = "list about")
            }
            Button(onClick = {
                thread {
                    Thread.sleep(5000)
                    runOnUiThread {
                        Toast.makeText(this@KotlinActivity, "1", Toast.LENGTH_SHORT).show()
                    }
                }
            }) {
                Text(text = "leak")
            }
        }
    }

    private var theList = arrayListOf<User>().apply {
        for (i in 0..8) add(User(i, "王雷$i"))
    }
    private var index = 10
    private fun operateList() {
        val oldData = theList.toList()
        for (i in oldData.indices) {
            Log.i("operateList", "operateList: $i ,${oldData[i].name}")
        }
        Log.i("operateList", "operateList: ---------------------------")
        theList.clear()
        for (i in 0..8) {
            val theIndex = if (i == 4) i + index else i
            theList.add(User(theIndex, "韩梅梅$theIndex"))
        }
        for (i in theList.indices) {
            Log.i("operateList", "operateList: $i ,${theList[i].name}")
        }
        launchFish {
            Log.i(KotlinActivity::class.simpleName, "before suspend")
            val studentInfo = getStuInfo3()
            Log.i(KotlinActivity::class.simpleName, "after suspend student name:${studentInfo}")
        }
    }

    private fun <T> launchFish(block: suspend () -> T) {
        var coroutine = block.createCoroutine(object : Continuation<T> {
            override val context: CoroutineContext get() = EmptyCoroutineContext
            override fun resumeWith(result: Result<T>) {
                Log.i(KotlinActivity::class.simpleName, "result$result")
            }
        })
        coroutine.resume(Unit)
    }

    private suspend fun getStuInfo3(): String {
        return suspendCoroutine {
            thread {
                Thread.sleep(3000)
                val name = "getStuInfo3: yes"
                Log.i(KotlinActivity::class.simpleName, "resume coroutine")
                it.resumeWith(Result.success(name))
            }
            Log.i(KotlinActivity::class.simpleName, "suspendCoroutine end")
        }
    }

    private fun runBlockingAbout() {
        runBlocking {
            val theLaunch = launch(context = Dispatchers.IO) {

                repeat(1000) {
                    Log.i(
                        "kotlin",
                        "runBlockingAbout launch thread = ${Thread.currentThread().name}"
                    )
                    Log.i("kotlin", "runBlockingAbout: $it")
                    delay(500L)
                }
            }
            val job = async {
                delay(500)
                Log.i("kotlin", "runBlockingAbout async thread = ${Thread.currentThread().name}")
                return@async "hello job"
            }
            Log.i("kotlin", "runBlockingAbout: ${job.await()}")
            Log.i("kotlin", "runBlockingAbout runBlocking thread = ${Thread.currentThread().name}")
            delay(1300)
            Log.i("kotlin", "runBlockingAbout: main is waiting")
            theLaunch.cancel()
            theLaunch.join()
            Log.i("kotlin", "runBlockingAbout: main will exit")
        }
    }

    suspend fun getHtml(): String = run {
        return withContext(AndroidCommonPool) {
            URL("http://www.baidu.com").readText()
        }
    }

    object AndroidCommonPool : CoroutineDispatcher() {
        override fun dispatch(context: CoroutineContext, block: Runnable) {
            AsyncTask.THREAD_POOL_EXECUTOR.execute(block)
        }
    }


    private fun other(user: User) {
        user.takeIf { it.name.isNotEmpty() }?.also { println("姓名为${it.name}") }
            ?: println("姓名为空")
        user.takeUnless { it.name.isNotEmpty() }?.also { println("姓名为空") }
            ?: println("姓名${user.name}")
        repeat(5) {
            println(user.name)
            println(it)
        }
    }

    class User(var age: Int, var name: String)
}