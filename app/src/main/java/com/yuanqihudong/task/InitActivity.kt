package com.yuanqihudong.task

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.jeremyliao.liveeventbus.LiveEventBus
import com.yuanqihudong.task.act.*
import com.yuanqihudong.task.bean.CustomMessage


class InitActivity : AppCompatActivity() {

    private val list = listOf(
        CustomMessage(ComposeActivity::class.simpleName, ComposeActivity::class),
        CustomMessage(CustomViewActivity::class.simpleName, CustomViewActivity::class),
        CustomMessage(SVGAActivity::class.simpleName, SVGAActivity::class),
        CustomMessage(PickerActivity::class.simpleName, PickerActivity::class),
        CustomMessage(BroadcastActivity::class.simpleName, BroadcastActivity::class),
        CustomMessage(MusicServiceActivity::class.simpleName, MusicServiceActivity::class),
        CustomMessage(ContentProviderActivity::class.simpleName, ContentProviderActivity::class),
        CustomMessage(ActiveActivity::class.simpleName, ActiveActivity::class),
        CustomMessage(FragmentationActivity::class.simpleName, FragmentationActivity::class),
        CustomMessage(NewsActivity::class.simpleName, NewsActivity::class),
        CustomMessage(NetActivity::class.simpleName, NetActivity::class)

    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Greet()
        }
    }

    @Preview
    @Composable
    fun Greet() {
        LazyColumn(
            contentPadding = PaddingValues(horizontal = 16.dp, vertical = 8.dp),
            modifier = Modifier.fillMaxWidth()
        ) {
            items(list.size) { index ->
                Text(
                    list[index].name ?: "",
                    textAlign = TextAlign.Center,
                    color = Color.Black,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(10.dp)
                        .clickable {
                            startActivity(Intent(this@InitActivity, list[index].clazz.java))
                        }
                        .background(Color.LightGray, CircleShape)
                        .padding(10.dp))
            }
        }
    }
}
