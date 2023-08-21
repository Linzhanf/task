package com.yuanqihudong.task.act

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.yuanqihudong.task.base.BaseActivity
import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.File
import java.io.FileInputStream
import java.io.FileOutputStream
import java.io.FileReader
import java.io.FileWriter
import java.io.IOException
import java.lang.Exception

class DownloadActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Surface(modifier = Modifier.fillMaxSize(), color = MaterialTheme.colors.background) {
                MyView()
            }
        }
    }

    private var list = arrayListOf<String>().apply {
        add("file-write-read")
        add("buffer-file-write-read")
        add("file-output-input-stream")
    }

    @Preview
    @Composable
    fun MyView() {
        LazyColumn(contentPadding = PaddingValues(horizontal = 16.dp, vertical = 8.dp), modifier = Modifier.fillMaxWidth()) {
            items(list.size) { index ->
                Text(list[index], textAlign = TextAlign.Center, color = Color.Black, modifier = Modifier
                        .fillMaxWidth()
                        .padding(10.dp)
                        .clickable {
                            when (index) {
                                0 -> fileWriteMethod()
                                1 -> fileBufferWriteMethod()
                                2 -> fileOutputStreamMethod()
                            }
                        }
                        .background(Color.LightGray, CircleShape)
                        .padding(10.dp))
            }
        }
    }

    /** -------------------------------------File Write Read------------------------------------ */
    private fun fileWriteMethod() {
        val fileName = "first_write_file.txt"
        val content = "This is a test file with FileWrite and FileRead : ${System.currentTimeMillis()}"
        var fileWriter: FileWriter? = null
        try {
            val file = File(getExternalFilesDir(null), fileName)
            fileWriter = FileWriter(file.path)
            fileWriter.write(content)
            fileWriter.close()
        } catch (e: IOException) {
            e.printStackTrace()
        } finally {
            try {
                fileWriter?.close()
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
        fileReadMethod(fileName)
    }

    private fun fileReadMethod(fileName: String) {
        var fileReader: FileReader? = null
        var content = ""
        try {
            val file = File(getExternalFilesDir(null), fileName)
            fileReader = FileReader(file.path)
            var line = fileReader.read()
            while (line != -1) {
                content += line.toChar()
                line = fileReader.read()
            }
            toast(content)
        } catch (e: IOException) {
            e.printStackTrace()
        } finally {
            try {
                fileReader?.close()
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }
    /** -------------------------------------File Write Read------------------------------------ */

    /** -----------------------------File Buffered Write Reader --------------------------------- */
    private fun fileBufferWriteMethod() {
        val fileName = "first_buffer_write_file.txt"
        val content = "This is a test file with BufferFileWrite and BufferFileRead : ${System.currentTimeMillis()}"
        var bufferWriter: BufferedWriter? = null
        try {
            val file = File(getExternalFilesDir(null), fileName)
            bufferWriter = BufferedWriter(FileWriter(file.path))
            bufferWriter.write(content)
            bufferWriter.close()
        } catch (e: IOException) {
            e.printStackTrace()
        } finally {
            try {
                bufferWriter?.close()
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
        fileBufferReadMethod(fileName)
    }

    private fun fileBufferReadMethod(fileName: String) {
        var content = ""
        var bufferWriter: BufferedReader? = null
        try {
            val file = File(getExternalFilesDir(null), fileName)
            //BufferedReader(InputStreamReader(FileInputStream(file)))
            bufferWriter = BufferedReader(FileReader(file))
            var line = bufferWriter.readLine()
            while (line != null) {
                content += line
                line = bufferWriter.readLine()
            }
            bufferWriter.close()
            toast(content)
        } catch (e: IOException) {
            e.printStackTrace()
        } finally {
            try {
                bufferWriter?.close()
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }
    /** -----------------------------File Buffered Write Reader --------------------------------- */

    /** -------------------------File FileInputStream FileOutputStream -------------------------- */
    private fun fileOutputStreamMethod() {
        val fileName = "first_output_input_file.txt"
        val content = "This is a test file with FileOutputStream and FileInputStream : ${System.currentTimeMillis()}"
        var fileOutputStream: FileOutputStream? = null
        try {
            val file = File(getExternalFilesDir(null), fileName)
            fileOutputStream = FileOutputStream(file)
            fileOutputStream.write(content.toByteArray())
            fileOutputStream.close()
        } catch (e: IOException) {
            e.printStackTrace()
        } finally {
            try {
                fileOutputStream?.close()
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
        fileInputStreamMethod(fileName)
    }

    private fun fileInputStreamMethod(fileName: String) {
        var content = ""
        var fileInputStream: FileInputStream? = null
        try {
            val file = File(getExternalFilesDir(null), fileName)
            fileInputStream = FileInputStream(file)
            val buffer = ByteArray(1024)
            var bytesRead: Int
            while (fileInputStream.read(buffer).also { bytesRead = it } != -1) {
                content += String(buffer, 0, bytesRead)
            }
            fileInputStream.close()
            toast(content)
        } catch (e: IOException) {
            e.printStackTrace()
        } finally {
            try {
                fileInputStream?.close()
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }
    /** -------------------------File FileInputStream FileOutputStream -------------------------- */
}