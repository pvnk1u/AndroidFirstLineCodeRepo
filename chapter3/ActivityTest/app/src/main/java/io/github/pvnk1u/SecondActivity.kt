package io.github.pvnk1u

import android.content.Intent
import android.os.Bundle
import android.widget.Button

class SecondActivity : BaseActivity() {
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
        /*val extraData = intent.getStringExtra("extra_data")
        Log.d("SecondActivity","extra data is $extraData")*/
        /**
         * 在当前Activity被销毁的时候返回给上一个Activity
         */
        val button2: Button = findViewById(R.id.button2)
        button2.setOnClickListener{
            // 构建了一个Intent，只不过这个Intent仅仅用于传递数据而已，它没有指定任何的“意图”。紧接着把要传递的数据存放在Intent中，然后调用了setResult()方法。
            val intent = Intent()
            intent.putExtra("data_return","Hello FirstActivity")
            setResult(RESULT_OK,intent)
            // finish()方法来销毁当前Activity
            finish()
        }
    }
}