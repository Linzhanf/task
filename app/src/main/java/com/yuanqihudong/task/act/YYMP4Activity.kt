package com.yuanqihudong.task.act

import android.os.Bundle
import android.view.LayoutInflater
import com.yuanqihudong.task.base.BaseActivity
import com.yuanqihudong.task.databinding.LayoutYyMp4Binding
import com.yuanqihudong.task.utils.ELog
import com.yuanqihudong.task.utils.EvaDownloader
import com.yuanqihudong.task.utils.EvaVideoEntity
import java.net.URL

class YYMP4Activity : BaseActivity() {

    lateinit var mBinding: LayoutYyMp4Binding

    private var evaDownloader: EvaDownloader? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mBinding = LayoutYyMp4Binding.inflate(LayoutInflater.from(this))
        setContentView(mBinding.root)

        if (evaDownloader == null) {
            evaDownloader = EvaDownloader(this)
        }
        evaDownloader?.decodeFromURL(
            URL("http://werewolf-image.xiaobanhui.com/gifts/zhongqiuchuan/zhongqiuchuan_v1.mp4"),
            object : EvaDownloader.ParseCompletion {
                override fun onComplete(videoItem: EvaVideoEntity) {
                    play(videoItem)
                }

                override fun onError() {
                    ELog.e("TAG", "download error")
                }
            })
    }

    private fun play(videoInfo: EvaVideoEntity) {
        // 播放前强烈建议检查文件的md5是否有改变
        // 因为下载或文件存储过程中会出现文件损坏，导致无法播放
        Thread {
            val file = videoInfo.mCacheDir
            ELog.i("TAG", "play file address ${file.absolutePath}")
            if (!file.exists()) {
                ELog.e("TAG", "${file.absolutePath} is not exist")
                return@Thread
            }
            mBinding.eva.startPlay(file)
        }.start()
    }

}