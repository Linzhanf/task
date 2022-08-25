package com.yuanqihudong.task.viewmodel

import androidx.lifecycle.viewModelScope
import com.yuanqihudong.task.net.TaskClient
import com.yuanqihudong.task.net.Urls
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.channels.Channel.Factory.UNLIMITED
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import okhttp3.internal.concurrent.Task
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.io.IOException
import kotlin.concurrent.thread

class NewsViewModel : BaseViewModel() {

    val allIntent = Channel<AllIntent>(UNLIMITED)

    private val _state = MutableStateFlow<TaskState>(TaskState.BeforeLoad)
    val state = _state.asStateFlow()

    init {
        handleIntent()
    }

    private fun handleIntent() {
        viewModelScope.launch {
            allIntent.consumeAsFlow().collect {
                commentLoading(it)
            }
        }
    }

    private suspend fun intentToState(intent: AllIntent): TaskState {
        when (intent) {
            //加载英语句子
            is AllIntent.GetArticle -> return syncRequest()
        }
    }

    private suspend fun syncRequest() = run {
        try {
            val execute = withContext(Dispatchers.IO) {
                TaskClient.getService(Urls::class.java).getArticle().execute()
            }
            withContext(Dispatchers.Main) {
                if (execute.isSuccessful) {
                    TaskState.GetArticle(execute.body().toString())
                } else {
                    TaskState.ErrorData(execute.message())
                }
            }
        } catch (e: IOException) {
            TaskState.ErrorData(e.message ?: "")
        }
    }

    private fun commentLoading(intent: AllIntent) {
        viewModelScope.launch(context = (errorContext<Any> {
            _state.value = TaskState.AfterLoad
            _state.value = TaskState.ErrorData(it.message ?: "请求异常")
        } + Dispatchers.Main)) {
            _state.value = TaskState.DoLoad
            _state.value = intentToState(intent)
            thread {
                Thread.sleep(10000)
                _state.value = TaskState.AfterLoad
            }
        }
    }
}