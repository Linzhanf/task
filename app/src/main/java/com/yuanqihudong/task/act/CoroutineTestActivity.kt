package com.yuanqihudong.task.act

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.LayoutInflater
import androidx.lifecycle.lifecycleScope
import com.yuanqihudong.task.base.BaseActivity
import com.yuanqihudong.task.databinding.ActCoroutineTestBinding
import kotlinx.coroutines.*
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.Dispatchers.Main
import kotlinx.coroutines.sync.Mutex
import kotlinx.coroutines.sync.withLock
import java.util.concurrent.Executor
import java.util.concurrent.Executors
import java.util.concurrent.TimeUnit
import kotlin.concurrent.thread
import kotlin.system.measureTimeMillis

class CoroutineTestActivity : BaseActivity() {

    private lateinit var mBinding: ActCoroutineTestBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mBinding = ActCoroutineTestBinding.inflate(LayoutInflater.from(this))
        setContentView(mBinding.root)

        init()
        //lock()
    }

    private fun init() {
        // 线程 thread
        thread(name = "t1") {
            val message = run {
                Thread.sleep(1000)
                Thread.currentThread().name
            }
            Handler(Looper.getMainLooper()).post {
                mBinding.content.text = "threadName:$message\n"
            }
        }
        // 线程 CoroutineScope
        CoroutineScope(Main).launch {
            var message = withContext(IO) {
                delay(2000)
                Thread.currentThread().name
            }
            message += waitThread()
            mBinding.content.text = mBinding.content.text.toString().plus("threadName2:$message")
        }
        // 线程池1
        /*val service = Executors.newFixedThreadPool(10)
        repeat(100000) {
            service.execute { Thread.sleep(100) }
        }
        service.shutdown()
        service.awaitTermination(Long.MAX_VALUE, TimeUnit.SECONDS)
        // 线程池2
        CoroutineScope(IO).launch {
            val context = Executors.newFixedThreadPool(10).asCoroutineDispatcher()
            repeat(100000) {
                launch(context) {
                    delay(100)
                }
            }
        }*/

    }

    private suspend fun waitThread(): String {
        delay(3000)
        return Thread.currentThread().name
    }

    private fun lock() {
        CoroutineScope(Main).launch {
            var sum = 0
            var lockSum = 0
            var mutexSum = 0
            val mutex = Mutex()
            measureTimeMillis {
                coroutineScope {
                    repeat(10000) {
                        launch(IO) { sum += 1 }
                        launch(IO) { sum += 1 }
                        launch { synchronized(this@coroutineScope) { lockSum += 1 } }
                        launch { synchronized(this@coroutineScope) { lockSum += 1 } }
                        launch(IO) { mutex.withLock { mutexSum += 1 } }
                        launch(IO) { mutex.withLock { mutexSum += 1 } }
                    }
                }
            }
            launch(Main) {
                mBinding.content.text = "sum:$sum\nlockSum:$lockSum\nmutexSum:$mutexSum\n"
            }
        }
    }

}

