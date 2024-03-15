package io.github.pvnk1u

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.Toast

class FirstActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        /**
         * 调用setContentView方法来给当前的Activity加载一个布局
         * 在setContentView()方法中，一般会传入一个布局文件的id。
         * 这里只需要调用R.layout.first_layout就可以得到first_layout.xml布局的id
         */
        setContentView(R.layout.first_layout)

        /**
         * 在Activity中，可以通过findViewById()方法获取在布局文件中定义的元素，这里传入
         * R.id.button1来得到按钮的实例，这个值是在first_layout.xml中通过android:id属
         * 性指定的。findViewById()方法返回的是一个继承自View的泛型对象，因此Kotlin无法自动
         * 推导出它是一个Button还是其他控件，所以需要将button1变量显式地声明成Button类
         * 型。得到按钮的实例之后，通过调用setOnClickListener()方法为按钮注册一个监听
         * 器，点击按钮时就会执行监听器中的onClick()方法。因此，弹出Toast的功能当然是要在
         * onClick()方法中编写了。
         */
        val button1: Button = findViewById(R.id.button1)
        button1.setOnClickListener{
            /**
             * Toast提醒
             *
             * Toast是Android系统提供的一种非常好的提醒方式，在程序中可以使用它将一些短小的信息通
             * 知给用户，这些信息会在一段时间后自动消失，并且不会占用任何屏幕空间，
             *
             * makeText()方法需要传入3个参数。第一个参数是Context，也就是Toast要求的上下文，
             * 由于Activity本身就是一个Context对象，因此这里直接传入this即可。
             * 第二个参数是Toast显示的文本内容。第三个参数是Toast显示的时
             * 长，有两个内置常量可以选择：Toast.LENGTH_SHORT和Toast.LENGTH_LONG。
             */
            Toast.makeText(this,"You clicked Button 1",Toast.LENGTH_SHORT).show()
        }
    }
}