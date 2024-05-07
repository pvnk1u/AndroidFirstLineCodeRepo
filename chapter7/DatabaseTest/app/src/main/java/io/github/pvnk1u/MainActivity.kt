package io.github.pvnk1u

import android.annotation.SuppressLint
import android.content.ContentValues
import android.os.Bundle
import android.util.Log
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
    @SuppressLint("Range")
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


        /**
         * 增加数据的逻辑
         */
        val addData : Button = findViewById(R.id.addData)
        addData.setOnClickListener{
            val db = dbHelper.writableDatabase
            val values1 = ContentValues().apply{
                // 开始组装第一条数据
                put("name","The Da Vinci Code")
                put("author","Dan Brown")
                put("pages",454)
                put("price",16.96)
            }
            // 插入第一条数据
            db.insert("Book",null,values1)
            val values2 = ContentValues().apply {
                // 开始组装第二条数据
                put("name","The Lost symbol")
                put("author","Dan Brown")
                put("pages",510)
                put("price",19.95)
            }
            // 插入第二条数据
            db.insert("Book",null,values2)
        }
        /**
         * 更新数据的逻辑
         */
        val updateData: Button = findViewById(R.id.updateData)
        updateData.setOnClickListener{
            val db = dbHelper.writableDatabase
            val values = ContentValues()
            //想把价格这一列的数据更新成10.99
            values.put("price",10.99)
            /**
             * 然后调用了SQLiteDatabase的update()方法执行具体的更新操作，可以看到，这里使用了第三、第四个参数来指定具体更新
             * 哪几行。第三个参数对应的是SQL语句的where部分，表示更新所有name等于?的行，而?是一
             * 个占位符，可以通过第四个参数提供的一个字符串数组为第三个参数中的每个占位符指定相应
             * 的内容，arrayOf()方法是Kotlin提供的一种用于便捷创建数组的内置方法。
             */
            db.update("Book",values,"name = ?", arrayOf("The Da Vinci Code"))
        }

        /**
         * 删除数据的逻辑
         */
        val deleteData: Button = findViewById(R.id.deleteData)
        // 在删除按钮的点击事件里指明删除Book表中的数据，并且通过第二、第三个参数来指定仅删除那些页数超过500页的书
        deleteData.setOnClickListener{
            val db = dbHelper.writableDatabase
            db.delete("Book", "pages > ?", arrayOf("500"))
        }


        /**
         * 查询数据的逻辑
         */
        val queryData : Button = findViewById(R.id.queryData)
        queryData.setOnClickListener {
            val db = dbHelper.writableDatabase
            // 查询Book表中所有的数据
            val cursor = db.query("Book", null, null, null, null, null, null)
            if (cursor.moveToFirst()) {
                do {
                    // 遍历Cursor对象，取出数据并打印
                    val name = cursor.getString(cursor.getColumnIndex("name"))
                    val author = cursor.getString(cursor.getColumnIndex("author"))
                    val pages = cursor.getInt(cursor.getColumnIndex("pages"))
                    val price = cursor.getDouble(cursor.getColumnIndex("price"))
                    Log.d("MainActivity", "book name is $name")
                    Log.d("MainActivity", "book author is $author")
                    Log.d("MainActivity", "book pages is $pages")
                    Log.d("MainActivity", "book price is $price")
                } while (cursor.moveToNext())
            }
            // 最后关闭cursor
            cursor.close()
        }

    }
}