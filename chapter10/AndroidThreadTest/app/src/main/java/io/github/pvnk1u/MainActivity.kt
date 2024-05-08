package io.github.pvnk1u

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.os.Message
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import kotlin.concurrent.thread

class MainActivity : AppCompatActivity() {

    /**
     * 子线程中直接更新UI是不被允许的，会导致程序崩溃
     */
    /*override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        *//**
         * 设置更改按钮点击事件，点击后开启子线程修改TextView中的内容
         *//*
        *//*val changeTextBtn:Button = findViewById(R.id.changeTextBtn)
        val textView : TextView = findViewById(R.id.textView)
        changeTextBtn.setOnClickListener{
            thread {
                textView.text = "Nice to meet you"
            }
        }*//*


    }*/


    // 定义了一个整型变量updateText，用于表示更新TextView这个动作
    val updateText = 1


    /**
     * 新增一个Handler对象，并重写父类的handleMessage()方法，在这里对具体的Message进行处理。
     * 如果发现Message的what字段的值等于updateText，就将TextView显示的内容改成“Nice to meet you”。
     */
    val handler = object : Handler(Looper.getMainLooper()) {
        override fun handleMessage(msg: Message) {
            // 在这里可以进行UI操作
            val textView : TextView = findViewById(R.id.textView)
            when (msg.what) {
                updateText -> textView.text = "Nice to meet you"
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        /**
         * 没有在子线程里直接进行UI操作，而是创建了一个Message（android.os.Message）对象，并将它的
         * what字段的值指定为updateText，然后调用Handler的sendMessage()方法将这条
         * Message发送出去。很快，Handler就会收到这条Message，并在handleMessage()方法中
         * 对它进行处理。注意此时handleMessage()方法中的代码就是在主线程当中运行的了，所以
         * 我们可以放心地在这里进行UI操作。接下来对Message携带的what字段的值进行判断，如果等
         * 于updateText，就将TextView显示的内容改成“Nice to meet you”。
         */
        val changeTextBtn:Button = findViewById(R.id.changeTextBtn)
        changeTextBtn.setOnClickListener {
            thread {
                val msg = Message()
                msg.what = updateText
                handler.sendMessage(msg) // 将Message对象发送出去
            }
        }
    }



}