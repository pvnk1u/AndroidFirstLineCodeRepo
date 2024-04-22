package io.github.pvnk1u

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    lateinit var timeChangeReceiver : TimeChangeReceiver

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        /**
         * 创建了一个IntentFilter的实例，并给它添加了一个值为android.intent.action.TIME_TICK的action
         * 当系统时间发生变化时，系统发出的正是一条值为android.intent.action.TIME_TICK的广播，
         * 也就是说我们的BroadcastReceiver想要监听什么广播，就在这里添加相应的action。
         */
        val intentFilter = IntentFilter()
        intentFilter.addAction("android.intent.action.TIME_TICK")
        /**
         * 创建了一个TimeChangeReceiver的实例，然后调用registerReceiver()方法进行注册，
         * 将TimeChangeReceiver的实例和IntentFilter的实例都传了进去，这样
         * TimeChangeReceiver就会收到所有值为android.intent.action.TIME_TICK的广播，
         * 也就实现了监听系统时间变化的功能。
         */
        timeChangeReceiver = TimeChangeReceiver()
        registerReceiver(timeChangeReceiver,intentFilter)
    }

    /**
     * 要记得，动态注册的BroadcastReceiver一定要取消注册才行，这里是在
     * onDestroy()方法中通过调用unregisterReceiver()方法来实现的。
     */
    override fun onDestroy() {
        super.onDestroy()
        unregisterReceiver(timeChangeReceiver)
    }


    /**
     * 内部类TimeChangeReceiver，这个类是继承自BroadcastReceiver的，
     * 并重写了父类的onReceive()方法。这样每当系统时间发生变化时，
     * onReceive()方法就会得到执行，这里只是简单地使用Toast提示了一段文本信息
     */
    inner class TimeChangeReceiver : BroadcastReceiver(){

        override fun onReceive(context: Context?, intent: Intent?) {
            Toast.makeText(context,"Time has changed",Toast.LENGTH_SHORT).show()
        }
    }
}