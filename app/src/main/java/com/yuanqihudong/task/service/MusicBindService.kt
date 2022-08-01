package com.yuanqihudong.task.service

import android.app.Service
import android.content.Intent
import android.media.MediaPlayer
import android.os.Binder
import android.os.IBinder
import android.util.Log
import com.yuanqihudong.task.R
import java.lang.Exception

class MusicBindService : Service() {

    companion object {
        val TAG: String = javaClass.simpleName
    }

    private val mBinder = LocalBinder()

    override fun onBind(intent: Intent?): IBinder = mBinder

    inner class LocalBinder : Binder() {
        fun getService() = this@MusicBindService
    }

    private var mMediaPlayer: MediaPlayer? = null

    override fun onCreate() {
        Log.i(TAG, "onCreate: ")
        super.onCreate()
        if (mMediaPlayer == null) {
            mMediaPlayer = MediaPlayer.create(this, R.raw.avchat_ring)
            mMediaPlayer?.isLooping = true
        }
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        val binder = intent?.extras
        when (binder?.getInt("op")) {
            1 -> play()
            2 -> stop()
            3 -> pause()
        }
        return super.onStartCommand(intent, flags, startId)
    }

    fun pause() {
        Log.i(TAG, "onPause: ")
        if (mMediaPlayer?.isPlaying == true) {
            mMediaPlayer?.pause()
        }
    }

    fun stop() {
        Log.i(TAG, "onStop: ")
        mMediaPlayer?.stop()
        try {
            mMediaPlayer?.prepare()
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    fun play() {
        Log.i(TAG, "onPlay: ")
        if (mMediaPlayer?.isPlaying == false) {
            mMediaPlayer?.start()
        }
    }

    override fun onDestroy() {
        Log.i(TAG, "onDestroy: ")
        super.onDestroy()
        mMediaPlayer?.stop()
        mMediaPlayer?.release()
    }
}