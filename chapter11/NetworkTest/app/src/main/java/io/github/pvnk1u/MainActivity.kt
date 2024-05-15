package io.github.pvnk1u

import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import java.io.BufferedReader
import java.io.InputStreamReader
import java.net.HttpURLConnection
import java.net.URL
import kotlin.concurrent.thread

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        /**
         * 发送请求按钮点击事件
         */
        val sendRequestBtn: Button = findViewById(R.id.sendRequestBtn)
        sendRequestBtn.setOnClickListener{
            sendRequestWithHttpURLConnection()
        }
    }

    private fun sendRequestWithHttpURLConnection(){
        // 开启线程发起网络请求
        thread {
            var connection : HttpURLConnection ?= null
            try {
                val response = StringBuilder()
                val url = URL("https:www.bing.com")
                connection = url.openConnection() as HttpURLConnection
                connection.connectTimeout = 8000
                connection.readTimeout = 8000
                val input = connection.inputStream
                // 下面对获取到的输入流进行读取
                val reader = BufferedReader(InputStreamReader(input))
                reader.use {
                    reader.forEachLine {
                        response.append(it)
                    }
                }
                showResponse(response.toString())
            }catch (e:Exception){
                e.printStackTrace()
            }finally {
                connection?.disconnect()
            }
        }
    }

    private fun showResponse(response: String){
        /**
         * Android是不允许在子线程中进行UI操作的。
         * runOnUiThread()方法其实就是对异步消息处理机制进行了一层封装
         */
        runOnUiThread{
            // 在这里进行UI操作，将结果显示到界面上
            val responseText : TextView = findViewById(R.id.responseText)
            responseText.text = response
        }
    }
}