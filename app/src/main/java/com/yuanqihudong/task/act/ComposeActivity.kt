package com.yuanqihudong.task.act

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Button
import androidx.compose.material.Card
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.LinearProgressIndicator
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.material.TextFieldColors
import androidx.compose.material.TextFieldDefaults
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.res.imageResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.yuanqihudong.task.R
import com.yuanqihudong.task.base.BaseActivity


class ComposeActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Surface(modifier = Modifier.fillMaxSize(), color = MaterialTheme.colors.background) {
                SimpleWidget()
            }
        }
    }

    @Preview
    @Composable
    fun SimpleWidget() {
        var number by remember {
            mutableStateOf(0)
        }
        Column(
                modifier = Modifier.verticalScroll(rememberScrollState()),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.SpaceEvenly
        ) {
            Text(text = "hello world! - $number", color = Color.Blue, fontSize = 16.sp, modifier = Modifier.clickable {
                toast(number.toString())
            })
            Button(onClick = { number++ }) {
                Text(text = "button", color = Color.White, fontSize = 14.sp)
            }
            TextField(
                    value = "",
                    placeholder = { Text(text = "the editText placehoder") },
                    colors = TextFieldDefaults.textFieldColors(backgroundColor = Color.White),
                    onValueChange = { toast(it) }
            )
            Image(painter = painterResource(id = R.mipmap.ic_launcher), contentDescription = "logo")
            Image(bitmap = ImageBitmap.imageResource(id = R.mipmap.ic_launcher), contentDescription = "bitmap logo")
            AsyncImage(
                    model = "https://img-blog.csdnimg.cn/20200401094829557.jpg",
                    modifier = Modifier
                            .width(100.dp)
                            .height(100.dp),
                    contentDescription = "First line of code"
            )
            CircularProgressIndicator(color = Color.Green, strokeWidth = 5.dp)
            LinearProgressIndicator(backgroundColor = Color.Gray, color = Color.Black)
        }
    }

    @Preview
    @Composable
    fun HelloWorld() {
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