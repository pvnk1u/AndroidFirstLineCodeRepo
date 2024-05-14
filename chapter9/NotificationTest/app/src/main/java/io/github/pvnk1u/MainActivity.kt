package io.github.pvnk1u

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
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
             * 构建PendingIntent，用于通知点击事件时跳转向其他Activity
             */
            val intent = Intent(this,NotificationActivity::class.java)
            val pi = PendingIntent.getActivity(this,0,intent, PendingIntent.FLAG_IMMUTABLE)
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
                /**
                 * 设置此通知的点击事件
                 */
                .setContentIntent(pi)
                /**
                 * 设置通知点击后自动关闭显示
                 */
                .setAutoCancel(true)
                /**
                 * 默认是短文本通知，文本较长显示不全时可以使用setStyle设置内容
                 */
                /*.setStyle(NotificationCompat.BigTextStyle().bigText("Learn how to build notifications, send and sync data, " +
                        "and use voice actions. Get the official Android IDE and developer tools to build apps for Android."))*/
                /**
                 * 除了显示长文字之外，通知里还可以显示一张大图片，具体用法是基本相似的
                 */
                /*.setStyle(NotificationCompat.BigPictureStyle().bigPicture(
                    BitmapFactory.decodeResource(resources, R.drawable.big_image)))*/
                .build()
            manager.notify(1,notification)
        }
    }
}