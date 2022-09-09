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

class SecondViewModel : BaseViewModel() {

}