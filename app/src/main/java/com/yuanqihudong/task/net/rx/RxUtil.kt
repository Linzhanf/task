package com.yuanqihudong.task.net.rx

import com.yuanqihudong.task.net.bean.BusinessException
import com.yuanqihudong.task.net.bean.ServiceResult
import com.yuanqihudong.task.utils.GsonUtil
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.core.ObservableTransformer
import io.reactivex.rxjava3.schedulers.Schedulers
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.RequestBody
import okhttp3.RequestBody.Companion.toRequestBody
import org.json.JSONObject
import java.util.IdentityHashMap
import java.util.UUID

object RxUtil {

    fun getQueryMapByJson(obj: Any) = run {
        IdentityHashMap<String, String>().apply {
            runCatching {
                val jsonObject = JSONObject(GsonUtil.toJson(obj))
                val keys = jsonObject.keys()
                while (keys.hasNext()) {
                    val key = keys.next()
                    this[key] = jsonObject[key].toString()
                }
            }
        }
    }

    fun getPartMapByJson(obj: Any) = run {
        HashMap<String, RequestBody>().apply {
            runCatching {
                val jsonObject = JSONObject(GsonUtil.toJson(obj))
                val keys = jsonObject.keys()
                while (keys.hasNext()) {
                    val key = keys.next()
                    jsonObject.get(key)?.let {
                        this[key] = it.toString().toRequestBody("text/plain".toMediaTypeOrNull())
                    }
                    val uuid = UUID.randomUUID().toString()
                    this[uuid] = uuid.toRequestBody("text/plain".toMediaTypeOrNull())
                }
            }
        }
    }

    fun getBodyPartByJson(obj: Any) = run {
        arrayListOf<MultipartBody.Part>().apply {
            runCatching {
                val jsonObject = JSONObject(GsonUtil.toJson(obj))
                val keys = jsonObject.keys()
                while (keys.hasNext()) {
                    val key = keys.next()
                    add(MultipartBody.Part.createFormData(key, jsonObject.get(key).toString()))
                }
            }
        }
    }

    fun <T : Any> handleResultOnIO() = run {
        try {
            ObservableTransformer { observable ->
                observable.subscribeOn(Schedulers.io()).flatMap {
                    if (it.errorCode == 0) {
                        it.data?.let { data -> Observable.just(data) } ?: run { Observable.empty() }
                    } else {
                        Observable.error(BusinessException(it.errorCode, it.errorMessage))
                    }
                }
            }
        } catch (e: Exception) {
            ObservableTransformer<ServiceResult<T>, T> {
                it.subscribeOn(Schedulers.io()).flatMap { Observable.error(Throwable("服务器错误")) }
            }
        }
    }

    fun <T : Any> handleResultOnMain() = run {
        ObservableTransformer<ServiceResult<T>, T> {
            it.compose(handleResultOnIO()).observeOn(AndroidSchedulers.mainThread())
        }
    }

    fun <T : Any> ioToMain(): ObservableTransformer<T, T> {
        return ObservableTransformer {
            it.subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
        }
    }
}