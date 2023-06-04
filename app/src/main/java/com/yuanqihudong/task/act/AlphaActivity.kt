package com.yuanqihudong.task.act

import android.os.Bundle
import android.view.LayoutInflater
import com.ss.ugc.android.alpha_player.IMonitor
import com.ss.ugc.android.alpha_player.IPlayerAction
import com.ss.ugc.android.alpha_player.controller.PlayerController
import com.ss.ugc.android.alpha_player.model.AlphaVideoViewType
import com.ss.ugc.android.alpha_player.model.Configuration
import com.ss.ugc.android.alpha_player.model.ScaleType
import com.ss.ugc.android.alpha_player.player.DefaultSystemPlayer
import com.ss.ugc.android.alpha_player.player.IMediaPlayer
import com.yuanqihudong.task.base.BaseActivity
import com.yuanqihudong.task.databinding.LayoutAlphaBinding
import com.yuanqihudong.task.utils.EvaDownloader

class AlphaActivity : BaseActivity() {

    lateinit var mBinding: LayoutAlphaBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mBinding = LayoutAlphaBinding.inflate(LayoutInflater.from(this))
        setContentView(mBinding.root)

        val config = Configuration(this, this)
        // 支持GLSurfaceView&GLTextureView, 默认使用GLSurfaceView
        config.alphaVideoViewType = AlphaVideoViewType.GL_TEXTURE_VIEW
        // 也可以设置自行实现的Player, demo中提供了基于ExoPlayer的实现
        val playerController = PlayerController.get(config, DefaultSystemPlayer())
        playerController.setPlayerAction(object : IPlayerAction {
            override fun onVideoSizeChanged(videoWidth: Int, videoHeight: Int, scaleType: ScaleType) {}

            override fun startAction() {}

            override fun endAction() {}
        })
        playerController.setMonitor(object : IMonitor {
            override fun monitor(result: Boolean, playType: String, what: Int, extra: Int, errorInfo: String) {}
        })

    }


}