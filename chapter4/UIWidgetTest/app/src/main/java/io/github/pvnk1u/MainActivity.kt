package io.github.pvnk1u

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
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
                /*val imageView: ImageView = findViewById(R.id.imageView)
                imageView.setImageResource(R.drawable.img_2)*/
                /**
                 * 点击逻辑，实现点击一下按钮让进度条消失，再点击一下按钮让进度条出现
                 */
                /*val progressBar: ProgressBar = findViewById(R.id.progressBar)
                if (progressBar.visibility == View.VISIBLE){
                    progressBar.visibility = View.GONE
                }else{
                    progressBar.visibility = View.VISIBLE
                }*/
                /**
                 * 点击逻辑，实现点击一次按钮，进度条进度加10
                 */
                /*val  progressBar : ProgressBar = findViewById(R.id.progressBar)
                progressBar.progress += 10*/
                /**
                 * 点击逻辑，删除前弹出一个确认对话框
                 *
                 * AlertDialog可以在当前界面弹出一个对话框，这个对话框是置顶于所有界面元素之上的，
                 * 能够屏蔽其他控件的交互能力，因此AlertDialog一般用于提示一些非常重要的内容或者警告信息。
                 */
                /**
                 * 首先通过AlertDialog.Builder构建一个对话框，这里使用了Kotlin标准函数中的apply函数。
                 * 在apply函数中为这个对话框设置标题、内容、可否使用Back键关闭对话框等属性，接下
                 * 来调用setPositiveButton()方法为对话框设置确定按钮的点击事件，调用
                 * setNegativeButton()方法设置取消按钮的点击事件，最后调用show()方法将对话框显示出来就可以了
                  */
                AlertDialog.Builder(this).apply {
                    setTitle("Something important.")
                    setMessage("Something important.")
                    setCancelable(false)
                    setPositiveButton("OK"){dialog,which->

                    }
                    setNegativeButton("Cancel"){dialog,which ->

                    }
                    show()
                }
            }
        }
    }
}