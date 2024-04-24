package io.github.pvnk1u

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.os.Bundle
import android.os.PersistableBundle
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    lateinit var timeChangeReceiver : TimeChangeReceiver

    /**
     * 注册时间广播监听器
     */
    /*override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        *//**
         * 创建了一个IntentFilter的实例，并给它添加了一个值为android.intent.action.TIME_TICK的action
         * 当系统时间发生变化时，系统发出的正是一条值为android.intent.action.TIME_TICK的广播，
         * 也就是说我们的BroadcastReceiver想要监听什么广播，就在这里添加相应的action。
         *//*
        val intentFilter = IntentFilter()
        intentFilter.addAction("android.intent.action.TIME_TICK")
        */
    /**
     * 创建了一个TimeChangeReceiver的实例，然后调用registerReceiver()方法进行注册，
     * 将TimeChangeReceiver的实例和IntentFilter的实例都传了进去，这样
     * TimeChangeReceiver就会收到所有值为android.intent.action.TIME_TICK的广播，
     * 也就实现了监听系统时间变化的功能。
     *//*
        timeChangeReceiver = TimeChangeReceiver()
        registerReceiver(timeChangeReceiver,intentFilter)
    }*/

    /**
     * 发送自定义广播
     */
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val button : Button = findViewById(R.id.button)
        /**
         * 在按钮的点击事件里面加入了发送自定义广播的逻辑
         */
        button.setOnClickListener{
            /**
             * 首先构建了一个Intent对象，并把要发送的广播的值传入。然后调用Intent的setPackage()
             * 方法，并传入当前应用程序的包名。packageName是getPackageName()的语法糖写法，用
             * 于获取当前应用程序的包名。最后调用sendBroadcast()方法将广播发送出去，这样所有监
             * 听io.github.pvnk1u.MY_BROADCAST这条广播的BroadcastReceiver就会收
             * 到消息了。此时发出去的广播就是一条标准广播。
             */
            val intent = Intent("io.github.pvnk1u.MY_BROADCAST")
            /**
             * 在Android8.0系统之后，静态注册的BroadcastReceiver是无法接收隐式广播的，而默认情况下发出
             * 的自定义广播恰恰都是隐式广播。因此这里一定要调用setPackage()方法，指定这条广播是
             * 发送给哪个应用程序的，从而让它变成一条显式广播，否则静态注册的BroadcastReceiver将
             * 无法接收到这条广播。
             */
            intent.setPackage(packageName)
            sendBroadcast(intent)
        }
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