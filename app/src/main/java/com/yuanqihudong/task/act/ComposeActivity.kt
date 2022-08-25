package com.yuanqihudong.task.act

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import com.yuanqihudong.task.R


class ComposeActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            helloWorld()
        }
    }

    @Preview
    @Composable
    fun helloWorld() {
        Card(modifier = Modifier.fillMaxSize()) {
            var expanded by remember { mutableStateOf(false) }
            Column(
                Modifier.clickable { expanded = !expanded },
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Image(
                    painter = painterResource(id = R.mipmap.ic_launcher),
                    contentDescription = null
                )
                AnimatedVisibility(visible = expanded) {
                    Text(
                        text = "hello compose",
                        style = MaterialTheme.typography.h2
                    )
                }
            }
        }
    }

    /*@Preview
    @Composable
    private fun Greet() {
        val padding = 16.dp
        Column(
            Modifier
                .clickable { }
                .padding(padding)
                .fillMaxWidth()) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                AsyncImage(
                    model = ImageRequest.Builder(LocalContext.current)
                        .data("http://pic.hnchumeng.com/FibbCXj56_PWb_bgBfCmu-EeyNL5?imageslim")
                        .size(Size.ORIGINAL)
                        .crossfade(true)
                        .build(),
                    modifier = Modifier.size(50.dp, 50.dp),
                    placeholder = painterResource(id = R.mipmap.ic_launcher),
                    contentDescription = "描述"
                )
                Column {
                    Text("伊布")
                    Text("16 age")
                }
            }
            Card(elevation = 4.dp) {
                AsyncImage(
                    model = ImageRequest.Builder(LocalContext.current)
                        .data("https://img-blog.csdnimg.cn/20210124002108308.png")
                        .size(Size.ORIGINAL)
                        .build(),
                    modifier = Modifier
                        .fillMaxWidth()
                        .fillMaxHeight(),
                    placeholder = painterResource(id = R.mipmap.ic_launcher),
                    contentDescription = "描述"
                )
            }
        }
    }*/
}