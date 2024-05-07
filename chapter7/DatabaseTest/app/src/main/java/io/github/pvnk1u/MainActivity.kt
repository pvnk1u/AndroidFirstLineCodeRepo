package io.github.pvnk1u

import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    /**
     * 这里我们在onCreate()方法中构建了一个MyDatabaseHelper对象，并且通过构造函数的参
     * 数将数据库名指定为BookStore.db，版本号指定为1，然后在“Create Database”按钮的点击
     * 事件里调用了getWritableDatabase()方法。这样当第一次点击“Create Database”按钮
     * 时，就会检测到当前程序中并没有BookStore.db这个数据库，于是会创建该数据库并调用
     * MyDatabaseHelper中的onCreate()方法，这样Book表也就创建好了，然后会弹出一个
     * Toast提示创建成功。再次点击“Create Database”按钮时，会发现此时已经存在
     * BookStore.db数据库了，因此不会再创建一次。
     */
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        // 调用自定义的SQLiteHelper执行创建数据库表的功能
        /**
         * 第三个参数用于指定数据库版本号，当后续的版本号比最开始的版本号大时，会触发MyDatabaseHelper的onUpgrade方法，用于升级数据库
         */
        val dbHelper = MyDatabaseHelper(this, "BookStore.db", 2)
        val createDatabase : Button = findViewById(R.id.createDatabase)
        createDatabase.setOnClickListener {
            dbHelper.writableDatabase
        }
    }
}