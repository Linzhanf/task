package com.yuanqihudong.task.act

import android.content.ComponentName
import android.content.Intent
import android.content.ServiceConnection
import android.os.Bundle
import android.os.IBinder
import android.widget.Toast
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.work.*
import com.yuanqihudong.task.base.BaseActivity
import com.yuanqihudong.task.service.*

class MusicServiceActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            Greet()
        }
    }

    var myIntent: Intent? = null

    private fun startService(op: Int) {
        myIntent = Intent(this, MusicService::class.java)
        when (op) {
            5 -> finish()
            4 -> {
                stopService(myIntent)
                finish()
            }
        }
        val bundle = Bundle().apply {
            putInt("op", op)
        }
        startService(myIntent?.apply {
            putExtras(bundle)
        })
    }

    private var mBinderService: MusicBindService? = null
    private val mServiceConnection = object : ServiceConnection {

        override fun onServiceConnected(name: ComponentName?, service: IBinder?) {
            val bindService = service as MusicBindService.LocalBinder
            mBinderService = bindService.getService()
        }

        override fun onServiceDisconnected(name: ComponentName?) {
            mBinderService = null
        }
    }

    private fun startBindService(op: Int) {
        myIntent = Intent(this, MusicBindService::class.java)
        when (op) {
            1 -> {
                if (mBinderService == null) {
                    bindService(myIntent?.apply {
                        putExtras(Bundle().apply {
                            putInt("op", op)
                        })
                    }, mServiceConnection, BIND_AUTO_CREATE)
                } else {
                    mBinderService?.play()
                }
            }
            2 -> mBinderService?.stop()
            3 -> mBinderService?.pause()
            5 -> finish()
            4 -> {
                mBinderService?.let {
                    mBinderService = null
                    unbindService(mServiceConnection)
                }
                finish()
            }
        }
    }

    @Preview
    @Composable
    private fun Greet() {

        val list = listOf(
            MusicList("play") { startService(1) },
            MusicList("stop") { startService(2) },
            MusicList("pause") { startService(3) },
            MusicList("close") { startService(4) },
            MusicList("exit") { startService(5) }
        )
        val bindList = listOf(
            MusicList("play") { startBindService(1) },
            MusicList("stop") { startBindService(2) },
            MusicList("pause") { startBindService(3) },
            MusicList("close") { startBindService(4) },
            MusicList("exit") { startBindService(5) }
        )
        LazyColumn(modifier = Modifier.fillMaxWidth()) {
            items(list.size) { index ->
                itemText(list[index].name, list[index].function)
            }
            item {
                Text(
                    text = "bind service", modifier = Modifier
                        .fillMaxWidth()
                        .padding(10.dp)
                )
            }
            items(bindList.size) { index ->
                itemText(bindList[index].name, bindList[index].function)
            }
            item {
                itemText("intent service") {
                    startService(Intent(this@MusicServiceActivity, MyIntentService::class.java))
                }
            }
            item {
                itemText("job intent service") {
                    MyJobIntentService.enqueue(this@MusicServiceActivity, Intent())
                }
            }
            item {
                itemText("job service") {
                    MyJobService.schedulePushWork(this@MusicServiceActivity)
                }
            }
            item {
                itemText("WorkManager") {
                    val data = Data.Builder().putInt(MyWorker.KEY_INT_ARG, 999).build()
                    val data2 = Data.Builder().putInt(MyWorker.KEY_INT_ARG, 100).build()
                    val constraints =
                        Constraints.Builder().setRequiredNetworkType(NetworkType.CONNECTED).build()
                    val mRequest = OneTimeWorkRequest.Builder(MyWorker::class.java)
                        .setConstraints(constraints).setInputData(data).build()
                    val mRequest2 = OneTimeWorkRequest.Builder(MyWorker::class.java)
                        .setConstraints(constraints).setInputData(data2).build()
                    //OneTimeWorkRequest:1次、PeriodicWorkRequest:周期,15min
                    //WorkManager.getInstance(this@MusicServiceActivity).enqueue(mRequest)
                    WorkManager.getInstance(this@MusicServiceActivity)
                        .beginWith(mRequest)
                        .then(mRequest2)
                        .enqueue()
                    WorkManager.getInstance(this@MusicServiceActivity)
                        .getWorkInfoByIdLiveData(mRequest.id)
                        .observe(this@MusicServiceActivity) {
                            if (it.state.isFinished) {
                                val str = it.outputData.getInt(MyWorker.KEY_RESULT, 0)
                                Toast.makeText(
                                    this@MusicServiceActivity,
                                    "$str",
                                    Toast.LENGTH_SHORT
                                ).show()
                                /*WorkManager.getInstance(this@MusicServiceActivity)
                                    .cancelWorkById(mRequest.id)*/
                            }
                        }
                }
            }
        }

    }

    @Composable
    fun itemText(name: String?, function: () -> Unit) {
        Text(
            name ?: "",
            textAlign = TextAlign.Center,
            color = Color.Blue,
            modifier = Modifier
                .fillMaxWidth()
                .padding(10.dp)
                .clickable { function.invoke() }
                .background(Color.LightGray, CircleShape)
                .padding(10.dp))
    }

    override fun onDestroy() {
        super.onDestroy()
        myIntent?.let {
            stopService(it)
        }
        mBinderService?.let {
            mBinderService = null
            unbindService(mServiceConnection)
        }
    }

    data class MusicList(
        val name: String? = null,
        val function: () -> Unit = {}
    )

    data class TestClass(val value: String? = null)
}