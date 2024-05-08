package io.github.pvnk1u

import android.app.Service
import android.content.Intent
import android.os.IBinder

class MyService : Service() {

    override fun onBind(intent: Intent): IBinder {
        TODO("Return the communication channel to the service.")
    }

    /**
     * onCreate()方法会在Service创建的时候调用
     */
    override fun onCreate() {
        super.onCreate()
    }

    /**
     * onStartCommand()方法会在每次Service启动的时候调用
     *
     * 通常情况下，如果我们希望Service一旦启动就立刻去执行某个动作，就可以将逻辑写在onStartCommand()方法里。
     */
    override fun onStartCommand(intent: Intent, flags: Int, startId: Int): Int {
        return super.onStartCommand(intent, flags, startId)
    }

    /**
     * onDestroy()方法会在Service销毁的时候调用
     *
     * 当Service销毁时，我们又应该在onDestroy()方法中回收那些不再使用的资源。
     */
    override fun onDestroy() {
        super.onDestroy()
    }
}