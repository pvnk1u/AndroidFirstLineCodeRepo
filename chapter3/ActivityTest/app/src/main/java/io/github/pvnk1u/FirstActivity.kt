package io.github.pvnk1u

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class FirstActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        /**
         * 调用setContentView方法来给当前的Activity加载一个布局
         * 在setContentView()方法中，一般会传入一个布局文件的id。
         * 这里只需要调用R.layout.first_layout就可以得到first_layout.xml布局的id
         */
        setContentView(R.layout.first_layout)
    }
}