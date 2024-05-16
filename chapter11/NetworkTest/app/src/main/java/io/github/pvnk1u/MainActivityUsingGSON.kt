package io.github.pvnk1u

import android.os.Bundle
import android.util.Log
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import okhttp3.OkHttpClient
import okhttp3.Request
import kotlin.concurrent.thread

class MainActivityUsingGSON : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        /**
         * 发送请求按钮点击事件
         */
        val sendRequestBtn: Button = findViewById(R.id.sendRequestBtn)
        sendRequestBtn.setOnClickListener{
            /**
             * 第一种方式：HttpURLConnection方式发送HTTP请求
             */
            // sendRequestWithHttpURLConnection()
            /**
             * 第二种方式：OkHttp方式发送HTTP请求
             */
            sendRequestWithOkHttp()
        }
    }

    /**
     * 使用OkHttp发送Http请求
     */
    private fun sendRequestWithOkHttp(){
        thread {
            try {
                val client = OkHttpClient()
                val request = Request.Builder()
                    // 指定访问的服务器地址是计算机本机
                    .url("http://192.168.0.104/get_data.json")
                    .build()
                val response = client.newCall(request).execute()
                val responseData = response.body?.string()
                if (responseData != null) {
                    parseJSONWithGSON(responseData)
                }
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }


    /**
     * 使用GSON解析JSON
     */
    private fun parseJSONWithGSON(jsonData:String){
        val gson = Gson()
        val typeOf = object : TypeToken<List<App>>() {}.type
        val appList = gson.fromJson<List<App>>(jsonData, typeOf)
        for (app in appList) {
            Log.d("MainActivity", "id is ${app.id}")
            Log.d("MainActivity", "name is ${app.name}")
            Log.d("MainActivity", "version is ${app.version}")
        }
    }

}