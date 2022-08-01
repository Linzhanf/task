package com.yuanqihudong.task.service

import android.app.Service
import android.content.Intent
import android.media.MediaPlayer
import android.os.IBinder
import android.util.Log
import com.yuanqihudong.task.R
import java.lang.Exception

class MusicService : Service() {

    companion object {
        val TAG: String = javaClass.simpleName
    }

    private var mMediaPlayer: MediaPlayer? = null

    override fun onBind(intent: Intent?): IBinder? = null

    override fun onCreate() {
        Log.i(TAG, "onCreate: ")
        super.onCreate()
        if (mMediaPlayer == null) {
            mMediaPlayer = MediaPlayer.create(this, R.raw.avchat_ring)
            mMediaPlayer?.isLooping = true
        }
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        Log.i(TAG, "onStartCommand: ")
        val bundle = intent?.extras
        when(bundle?.getInt("op")) {
            1 -> play()
            2 -> stop()
            3 -> pause()
        }
        return super.onStartCommand(intent, flags, startId)
    }

    private fun pause() {
        Log.i(TAG, "pause: ")
        if (mMediaPlayer?.isPlaying == true) {
            mMediaPlayer?.pause()
        }
    }

    private fun stop() {
        Log.i(TAG, "stop: ")
        mMediaPlayer?.stop()
        try {
            mMediaPlayer?.prepare()
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    private fun play() {
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