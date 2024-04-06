package io.github.pvnk1u

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity(),View.OnClickListener {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val button : Button  = findViewById(R.id.button)
        /**
         * 直接通过lambda表达式指定按钮点击事件的形式和当前Activity实现View.OnClickListener接口，
         * 并通过setOnClickListener方法指定当前Activity的两种方式都可以实现按钮的点击事件回调动作
         */
        button.setOnClickListener{
            // 点击逻辑,实现点击按钮时获取InputText中的输入内容并通过Toast弹窗形式弹框显示
            val editText: EditText = findViewById(R.id.editText)
            val inputText =  editText.text.toString()
            Toast.makeText(this,inputText,Toast.LENGTH_SHORT).show()
        }
        button.setOnClickListener(this)
    }


    /**
     * 实现View.OnClickListener
     */
    override fun onClick(v: View?) {
        when(v?.id){
            R.id.button -> {
                /**
                 * 点击逻辑,实现点击按钮时获取InputText中的输入内容并通过Toast弹窗形式弹框显示
                 */
                /*val editText: EditText = findViewById(R.id.editText)
                val inputText =  editText.text.toString()
                Toast.makeText(this,inputText,Toast.LENGTH_SHORT).show()*/
                /**
                 * 点击逻辑，实现点击按钮时动态地更改ImageView中图片的功能
                 */
                val imageView: ImageView = findViewById(R.id.imageView)
                imageView.setImageResource(R.drawable.img_2)
            }
        }
    }
}