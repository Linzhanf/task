package com.yuanqihudong.task.base

import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.trello.rxlifecycle4.components.support.RxAppCompatActivity

abstract class BaseActivity : RxAppCompatActivity() {

    fun toast(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_LONG).show()
    }

}