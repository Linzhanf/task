package com.yuanqihudong.task.act

import android.os.Bundle
import android.view.KeyEvent
import android.view.LayoutInflater
import android.webkit.WebResourceRequest
import android.webkit.WebView
import android.webkit.WebViewClient
import com.yuanqihudong.task.base.BaseActivity
import com.yuanqihudong.task.databinding.ActWebviewBinding

class WebViewActivity : BaseActivity() {

    private lateinit var mBinding: ActWebviewBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mBinding = ActWebviewBinding.inflate(LayoutInflater.from(this))
        setContentView(mBinding.root)

        mBinding.webView.apply {
            settings.javaScriptEnabled = true
            settings.useWideViewPort = true
            settings.allowFileAccessFromFileURLs = true
            settings.allowFileAccess = true
            settings.textZoom = 100
            loadUrl("https://beta.peach-live.com/front/act/act_week_star/index.html")

            webViewClient = object : WebViewClient() {

                override fun shouldOverrideUrlLoading(view: WebView?, url: String?): Boolean {
                    url?.let { view?.loadUrl(it) }
                    return true
                }
            }
        }
    }

    override fun onKeyDown(keyCode: Int, event: KeyEvent?): Boolean {
        if (keyCode == KeyEvent.KEYCODE_BACK && mBinding.webView.canGoBack()) {
            mBinding.webView.goBack()
            return true
        }
        return super.onKeyDown(keyCode, event)
    }

}