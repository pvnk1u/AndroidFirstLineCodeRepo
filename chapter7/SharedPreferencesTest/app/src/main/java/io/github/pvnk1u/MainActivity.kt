package io.github.pvnk1u

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        /**
         * 设置保存按钮的点击事件
         * */
        val saveButton:Button = findViewById(R.id.saveButton)
        saveButton.setOnClickListener{
            /**
             * 通过getSharedPreferences()方法指定SharedPreferences的文件名为data，并得到了
             * SharedPreferences.Editor对象。接着向这个对象中添加了3条不同类型的数据，最
             * 后调用apply()方法进行提交，从而完成了数据存储的操作。
             */
            val editor = getSharedPreferences("data",Context.MODE_PRIVATE).edit()
            editor.putString("name","Tom")
            editor.putInt("age",28)
            editor.putBoolean("married",false)
            editor.apply()
        }


        /**
         * 设置还原数据按钮的点击事件
         */
        val restoreButton : Button = findViewById(R.id.restoreButton)
        restoreButton.setOnClickListener {
            /**
             * 在还原数据按钮的点击事件中首先通过getSharedPreferences()方法得到
             * 了SharedPreferences对象，然后分别调用它的getString()、getInt()和
             * getBoolean()方法，去获取前面所存储的姓名、年龄和是否已婚，如果没有找到相应的值，
             * 就会使用方法中传入的默认值来代替，最后通过Log将这些值打印出来。
             */
            val prefs = getSharedPreferences("data", Context.MODE_PRIVATE)
            val name = prefs.getString("name", "")
            val age = prefs.getInt("age", 0)
            val married = prefs.getBoolean("married", false)
            Log.d("MainActivity", "name is $name")
            Log.d("MainActivity", "age is $age")
            Log.d("MainActivity", "married is $married")
        }
    }
}