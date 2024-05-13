package io.github.pvnk1u

import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.graphics.BitmapFactory
import android.os.Build
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.NotificationCompat

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        /**
         * 先获取NotificationManager的实例
         */
        val manager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
            /**
             * 创建了一个ID为normal的通知渠道
             *
             * 创建通知渠道的代码只在第一次执行的时候才会创建，当下次再执行创建代码时，系统会
             * 检测到该通知渠道已经存在了，因此不会重复创建，也并不会影响运行效率。
             */
            val channel = NotificationChannel("normal","Normal",NotificationManager.IMPORTANCE_DEFAULT)
            manager.createNotificationChannel(channel)
        }
        /**
         * 完成通知的创建工作
         */
        val sendNotice : Button = findViewById(R.id.sendNotice)
        sendNotice.setOnClickListener{
            /**
             * 注意，在NotificationCompat.Builder的构造函数中传入的渠道ID也必须叫
             * normal，如果传入了一个不存在的渠道ID，通知是无法显示出来的。
             */
            val notification = NotificationCompat.Builder(this,"normal")
                                .setContentTitle("This is content title")
                .setContentText("This is content text")
                .setSmallIcon(R.drawable.small_icon)
                .setLargeIcon(BitmapFactory.decodeResource(resources,
                    R.drawable.large_icon))
                .build()
            manager.notify(1,notification)
        }
    }
}