package io.github.pvnk1u

import android.app.Service
import android.content.Intent
import android.os.Binder
import android.os.IBinder
import android.util.Log

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
    override fun onCreate() {
        super.onCreate()
        Log.d("MyService","onCreate executed")
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