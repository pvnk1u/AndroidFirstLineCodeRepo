package io.github.pvnk1u

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity

class SecondActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.second_layout)
        /**
         * 获取其他Activity打开当前Activity时传递的数据
         */
        // 调用的是父类的getIntent()方法,该方法会获取用于启动SecondActivity的Intent，
        // 然后调用getStringExtra()方法并传入相应的键值，就可以得到传递的数据了。
        // 如果传递的是整型数据，则使用getIntExtra()方法；
        // 如果传递的是布尔型数据，则使用getBooleanExtra()方法，以此类推。
        val extraData = intent.getStringExtra("extra_data")
        Log.d("SecondActivity","extra data is $extraData")
    }
}