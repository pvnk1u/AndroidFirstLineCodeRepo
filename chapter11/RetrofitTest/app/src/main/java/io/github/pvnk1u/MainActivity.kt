package io.github.pvnk1u

import android.os.Bundle
import android.util.Log
import android.widget.Button
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val getAppDataBtn : Button = findViewById(R.id.getAppDataBtn)
        getAppDataBtn.setOnClickListener {
            /**
             * 先使用了Retrofit.Builder来构建一个Retrofit对象，
             * 其中baseUrl()方法用于指定所有Retrofit请求的根路径，
             *  addConverterFactory()方法用于指定Retrofit在解析数据时所使用的转换库，这里指定成
             * GsonConverterFactory。注意这两个方法都是必须调用的。
             */
            val retrofit = Retrofit.Builder()
                .baseUrl("http://192.168.0.103/")
                .addConverterFactory(GsonConverterFactory.create())
                .build()

            /**
             * 有了Retrofit对象之后，就可以调用它的create()方法，
             * 并传入具体Service接口所对应的Class类型，创建一个该接口的动态代理对象。
             */
            val appService = retrofit.create(AppService::class.java)
            appService.getAppData().enqueue(object : Callback<List<App>> {
                override fun onResponse(call: Call<List<App>>,
                                        response: Response<List<App>>
                ) {
                    val list = response.body()
                    if (list != null) {
                        for (app in list) {
                            Log.d("MainActivity", "id is ${app.id}")
                            Log.d("MainActivity", "name is ${app.name}")
                            Log.d("MainActivity", "version is ${app.version}")
                        }
                    }
                }
                override fun onFailure(call: Call<List<App>>, t: Throwable) {
                    t.printStackTrace()
                }
            })
        }
    }
}