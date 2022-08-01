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
}