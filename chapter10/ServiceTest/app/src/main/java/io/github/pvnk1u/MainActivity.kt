package io.github.pvnk1u

import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.content.ServiceConnection
import android.os.Bundle
import android.os.IBinder
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {


    lateinit var downloadBinder: MyService.DownloadBinder

    /**
     * 创建了一个ServiceConnection的匿名类实现，并在里面重写了
     * onServiceConnected()方法和onServiceDisconnected()方法。
     *
     *
     */
    private val connection = object : ServiceConnection{

        /**
         * onServiceConnected()方法方法会在Activity与Service成功绑定的时候调用
         */
        override fun onServiceConnected(name: ComponentName, service: IBinder) {
            /**
             * 在onServiceConnected()方法中，我们又通过向下转型得到了DownloadBinder的实例，
             * 有了这个实例，Activity和Service之间的关系就变得非常紧密
             * 了。现在我们可以在Activity中根据具体的场景来调用DownloadBinder中的任何public方
             * 法，即实现了指挥Service干什么Service就去干什么的功能。
             */
            downloadBinder = service as MyService.DownloadBinder
            downloadBinder.startDownload()
            downloadBinder.getProgress()
        }

        /**
         * onServiceDisconnected()方法只有在Service的创建进程崩溃或者被杀掉的时候才会调用，这个方法不太常用
         */
        override fun onServiceDisconnected(name: ComponentName) {
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        /**
         * 设置启动Service按钮的点击事件
         */
        val startServiceBtn : Button = findViewById(R.id.startServiceBtn)
        val stopServiceBtn : Button = findViewById(R.id.stopServiceBtn)
        startServiceBtn.setOnClickListener {
            val intent = Intent(this, MyService::class.java)
            startService(intent) // 启动Service
        }
        /**
         * 设置停止Service的点击事件
         */
        stopServiceBtn.setOnClickListener {
            val intent = Intent(this, MyService::class.java)
            stopService(intent) // 停止Service
        }

        val bindServiceBtn : Button = findViewById(R.id.bindServiceBtn)
        val unbindServiceBtn : Button = findViewById(R.id.unbindServiceBtn)
        /**
         * 设置绑定Service按钮的点击事件
         */
        bindServiceBtn.setOnClickListener {
            val intent = Intent(this, MyService::class.java)
            /**
             * 调用bindService将当前activity与service进行绑定
             *
             * bindService()方法接收3个参数，第一个参数就
             * 是刚刚构建出的Intent对象，第二个参数是前面创建出的ServiceConnection的实例，第三
             * 个参数则是一个标志位，这里传入BIND_AUTO_CREATE表示在Activity和Service进行绑定后
             * 自动创建Service。
             */
            bindService(intent, connection, Context.BIND_AUTO_CREATE) // 绑定Service
        }

        /**
         * 设置解绑Service按钮的点击事件
         */
        unbindServiceBtn.setOnClickListener {
            unbindService(connection) // 解绑Service
        }

    }
}