package io.github.pvnk1u

import android.content.Context
import android.os.Bundle
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.IOException
import java.io.InputStreamReader
import java.io.OutputStreamWriter

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        /**
         * 应用重启后重新加载onDestroy时保存的内容
         * */
        val editText : EditText = findViewById(R.id.editText)
        val inputText = load()
        if (inputText.isNotEmpty()){
            editText.setText(inputText)
            // 调用setSelection()方法将输入光标移动到文本的末尾位置以便继续输入
            editText.setSelection(inputText.length)
            Toast.makeText(this,"Restoring succeeded",Toast.LENGTH_SHORT).show()
        }
    }

    /**
     * 从文件中读取数据
     */
    private fun load(): String {
        val content = StringBuilder()
        try {
            // 将会读取/data/data/<package name>/files/data 文件中的内容
            val input = openFileInput("data")
            val reader = BufferedReader(InputStreamReader(input))
            reader.use {
                reader.forEachLine {
                    content.append(it)
                }
            }
        } catch (e: IOException) {
            e.printStackTrace()
        }
        return content.toString()
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