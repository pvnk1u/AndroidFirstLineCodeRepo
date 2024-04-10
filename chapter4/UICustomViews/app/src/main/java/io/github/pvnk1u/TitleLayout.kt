package io.github.pvnk1u

import android.app.Activity
import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.Button
import android.widget.LinearLayout
import android.widget.Toast

/**
 * 自定义的标题栏控件
 */
// 主构造函数中声明了Context和AttributeSet这两个参数，在布局中引入TitleLayout控件时就会调用这个构造函数。
class TitleLayout(context:Context,attrs: AttributeSet): LinearLayout(context,attrs) {


    /**
     * 在init结构体中需要对标题栏布局进行动态加载，这就要借助LayoutInflater来实现了。
     * 通过LayoutInflater的from()方法可以构建出一个LayoutInflater对象，然后调用inflate()方法就可以动态加载一个布局文件。
     * inflate()方法接收两个参数：第一个参数是要加载的布局文件的id，这里传入R.layout.title；
     * 第二个参数是给加载好的布局再添加一个父布局，这里想要指定为TitleLayout，于是直接传入this。
     */
    init {
        LayoutInflater.from(context).inflate(R.layout.title,this)
        val titleBack : Button =  findViewById(R.id.titleBack)
        val titleEdit : Button =  findViewById(R.id.titleEdit)
        // 返回按钮点击事件——>销毁当前Activity
        titleBack.setOnClickListener{
            // context参数实际上是一个Activity的实例，在返回按钮的点击事件里，要先将它转换成Activity类型，
            // 然后再调用finish()方法销毁当前的Activity。Kotlin中的类型强制转换使用的关键字是as。
            val activity= context as Activity
            activity.finish()
        }
        // 修改按钮点击事件
        titleEdit.setOnClickListener{
            Toast.makeText(context,"You clicked Edit Button",Toast.LENGTH_SHORT).show()
        }
    }
}