package io.github.pvnk1u

import android.content.Context
import android.os.Bundle
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import java.io.BufferedWriter
import java.io.IOException
import java.io.OutputStreamWriter

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    /**
     * 重写onDestroy方法，保证在Activity销毁之前一定会调用到这个方法
     *
     * 销毁前获取editText中的内容并保存到文件中
     */
    override fun onDestroy() {
        super.onDestroy()
        val editText : EditText = findViewById(R.id.editText)
        val inputText = editText.text.toString()
        save(inputText)
    }

    /**
     * 将输入框中的文本保存到文件中
     */
    private fun save(inputText:String){
        try {
            val output = openFileOutput("data",Context.MODE_PRIVATE)
            val writer = BufferedWriter(OutputStreamWriter(output))
            writer.use {
                it.write(inputText)
            }
        }catch (e: IOException){
            e.printStackTrace()
        }
    }
}