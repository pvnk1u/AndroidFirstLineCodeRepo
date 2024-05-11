package io.github.pvnk1u

import android.annotation.SuppressLint
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.app.Service
import android.content.Context
import android.content.Intent
import android.graphics.BitmapFactory
import android.os.Binder
import android.os.Build
import android.os.IBinder
import android.util.Log
import androidx.core.app.NotificationCompat

class MyService : Service() {

    /**
     * 创建了DownloadBinder的实例
     *
     */
    private val mBinder = DownloadBinder()

    /**
     * 继承自Binder，然后在它的内部提供了开始下载以及查看下载进度的方法。
     * 当然这只是两个模拟方法，并没有实现真正的功能，我们在这两个方法中分别打印了一行日志
     */
    class DownloadBinder : Binder(){
        fun startDownload(){
            Log.d("MyService","startDownload executed")
        }

        fun getProgress(): Int{
            Log.d("MyService","getProgress executed")
            return 0
        }
    }

    /**
     * 在onBind()方法里返回了这个实例
     */
    override fun onBind(intent: Intent): IBinder {
        return mBinder
    }

    /**
     * onCreate()方法会在Service创建的时候调用
     */
    @SuppressLint("ForegroundServiceType")
    override fun onCreate() {
        super.onCreate()
        Log.d("MyService","onCreate executed")
        /**
         * 以下为创建前台Service的代码
         */
        val manager = getSystemService(Context.NOTIFICATION_SERVICE) as
                NotificationManager
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val channel = NotificationChannel("my_service", "前台Service通知",
                NotificationManager.IMPORTANCE_DEFAULT)
            manager.createNotificationChannel(channel)
        }
        val intent = Intent(this, MainActivity::class.java)
        val pi = PendingIntent.getActivity(this, 0, intent, PendingIntent.FLAG_IMMUTABLE)
        val notification = NotificationCompat.Builder(this, "my_service")
            .setContentTitle("This is content title")
            .setContentText("This is content text")
            .setSmallIcon(R.drawable.small_icon) 
            .setLargeIcon(BitmapFactory.decodeResource(resources, R.drawable.large_icon))
            .setContentIntent(pi)
            .build()
        /**
         * 这个方法接收两个参数：第一个参数是通知的id，类似于notify()方法的第一个参数；第二个参数则是
         * 构建的Notification对象。调用startForeground()方法后就会让MyService变成一个前
         * 台Service，并在系统状态栏显示出来。
         */
        startForeground(1, notification)
    }

    /**
     * onStartCommand()方法会在每次Service启动的时候调用
     *
     * 通常情况下，如果我们希望Service一旦启动就立刻去执行某个动作，就可以将逻辑写在onStartCommand()方法里。
     */
    override fun onStartCommand(intent: Intent, flags: Int, startId: Int): Int {
        Log.d("MyService","onStartCommand executed")
        return super.onStartCommand(intent, flags, startId)
    }

    /**
     * onDestroy()方法会在Service销毁的时候调用
     *
     * 当Service销毁时，我们又应该在onDestroy()方法中回收那些不再使用的资源。
     */
    override fun onDestroy() {
        super.onDestroy()
        Log.d("MyService","onDestroy executed")
    }
}