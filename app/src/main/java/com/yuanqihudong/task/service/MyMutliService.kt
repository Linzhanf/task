package com.yuanqihudong.task.service

import android.annotation.SuppressLint
import android.app.IntentService
import android.app.job.JobInfo
import android.app.job.JobParameters
import android.app.job.JobScheduler
import android.app.job.JobService
import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.os.Handler
import android.os.Looper
import android.util.Log
import android.widget.Toast
import androidx.core.app.JobIntentService
import androidx.work.Data
import androidx.work.WorkManager
import androidx.work.Worker
import androidx.work.WorkerParameters
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.yuanqihudong.task.bean.CustomMessage
import java.util.*
import kotlin.collections.ArrayList

//启动方式和普通的service一样
class MyIntentService : IntentService("MyIntentService") {

    override fun onHandleIntent(intent: Intent?) {
        Toast.makeText(
            this@MyIntentService, "${Thread.currentThread().id}", Toast.LENGTH_SHORT
        ).show()
    }
}

//JobIntentService与IntentService都是可以用来执行后台任务，在Android O之前，使用起来效果没多大差别，
// 但是Android O以后JobIntentService不会立即执行，等手机进入一定状态后才会执行任务，所以不能用来执行及时的后台任务。
class MyJobIntentService : JobIntentService() {

    private val mHandler = Handler(Looper.getMainLooper())

    companion object {
        fun enqueue(context: Context, work: Intent) {
            enqueueWork(context, MyJobIntentService::class.java, 0x11, work)
        }
    }

    override fun onHandleWork(intent: Intent) {
        mHandler.post {
            Toast.makeText(
                this@MyJobIntentService, "${Thread.currentThread().id}", Toast.LENGTH_SHORT
            ).show()
        }
    }
}

@SuppressLint("SpecifyJobSchedulerIdRange")
class MyJobService : JobService() {

    private val mHandler = Handler(Looper.getMainLooper())


    override fun onStartJob(params: JobParameters?): Boolean {
        mHandler.post {
            val localCountry = Locale.getDefault().country
            val localLanguage = Locale.getDefault().language
            Toast.makeText(
                this@MyJobService,
                "Country=${localCountry},Language=${localLanguage}",
                Toast.LENGTH_SHORT
            ).show()
        }
        jobFinished(params, false)
        return false
    }

    override fun onStopJob(params: JobParameters?): Boolean {
        return false
    }

    companion object {

        var mIndex = 0

        fun schedulePushWork(context: Context) {


            val scheduler = context.getSystemService(Context.JOB_SCHEDULER_SERVICE) as JobScheduler
            val builder =
                JobInfo.Builder(++mIndex, ComponentName(context, MyJobService::class.java))
            //builder.setMinimumLatency(1000 * 10)//延迟多久后执行，毫秒
            //builder.setOverrideDeadline(1000 * 10 * 15)//最多延迟多久
            builder.setRequiredNetworkType(JobInfo.NETWORK_TYPE_ANY)//设置执行的网络条件
            builder.setRequiresDeviceIdle(false)//是否在空闲时执行
            builder.setRequiresCharging(false) //是否在充电时执行
            scheduler.schedule(builder.build())
        }
    }
}

class MyWorker(context: Context, workerParams: WorkerParameters) : Worker(context, workerParams) {

    companion object {
        const val KEY_INT_ARG = "INT"
        const val KEY_RESULT = "RESULT"
    }

    override fun doWork(): Result {
        val int = inputData.getInt(KEY_INT_ARG, 0)
        Log.i(MyWorker::class.simpleName, "TestWork: int:$int")
        val result = Data.Builder().putInt(KEY_RESULT, int).build()
        return Result.success(result)
    }

}

