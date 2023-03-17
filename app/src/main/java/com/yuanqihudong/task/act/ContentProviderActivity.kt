package com.yuanqihudong.task.act

import android.content.ContentValues
import android.net.Uri
import android.os.Bundle
import android.util.Log
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.yuanqihudong.task.base.BaseActivity
import com.yuanqihudong.task.viewmodel.ContentProviderViewModel
import java.lang.StringBuilder

class ContentProviderActivity : BaseActivity() {

    private val mModel: ContentProviderViewModel by viewModels()
    var res = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mModel.value.observe(this) {
            res = it
        }
        setContent {
            clickBtn()
        }
    }

    @Preview
    @Composable
    private fun clickBtn() {
        val content = mModel.value.observeAsState()
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        ) {
            Button(
                onClick = { requestDb() },
                modifier = Modifier.fillMaxWidth(),
                colors = ButtonDefaults.buttonColors(
                    backgroundColor = Color.Blue,
                    disabledBackgroundColor = Color.Gray
                )
            ) {
                Text(
                    text = "click Me",
                    modifier = Modifier.fillMaxWidth(),
                    color = Color.White,
                    textAlign = TextAlign.Center,
                    fontSize = 20.sp
                )
            }
            Text(text = "${content.value}")
        }
    }

    private fun requestDb() {
        val uriUser = Uri.parse("content://cn.scu.myprovider/user")
        val cursor = contentResolver.query(uriUser, arrayOf("_id", "name"), null, null, null)
        var index = 0
        while (cursor?.moveToNext() == true) {
            if (cursor.getInt(0) > index) {
                index = cursor.getInt(0)
            }
        }
        index += 1
        val values: ContentValues = with(ContentValues()) {
            put("_id", index)
            put("name", "linzf$index")
            this
        }
        contentResolver.insert(uriUser, values)
        val content = StringBuilder()
        val cursor2 = contentResolver.query(uriUser, arrayOf("_id", "name"), null, null, null)
        while (cursor2?.moveToNext() == true) {
            Log.i(
                this::class.simpleName,
                "requestDb: ${cursor2.getInt(0)} ${cursor2.getString(1)}"
            )
            content.append("requestDb: ${cursor2.getInt(0)} ${cursor2.getString(1)} \n")
        }
        mModel.value.postValue(content.toString())
        cursor?.close()
        cursor2?.close()
    }
}