package io.github.pvnk1u

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity

open class BaseActivity : AppCompatActivity() {

    lateinit var receiver: ForceOfflineReceiver

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        ActivityCollector.addActivity(this)
    }

    /**
     * 重写了onResume()和onPause()这两个生命周期方法，然后分别在这两个方法里注册和取消注册了ForceOfflineReceiver。
     */
    override fun onResume() {
        super.onResume()
        val intentFilter = IntentFilter()
        intentFilter.addAction("io.github.pvnk1u.broadcastbestpractice.FORCE_OFFLINE")
        receiver = ForceOfflineReceiver()
        /**
         * 最新版本的Android中，不能再使用registerReceiver(receiver, intentFilter)的方式注册广播接收器，
         * 必须显式指定第三个参数：RECEIVER_EXPORTED/RECEIVER_NOT_EXPORTED，用于表示此接收器是否接收其他APP的广播
         */
        registerReceiver(receiver, intentFilter,RECEIVER_NOT_EXPORTED)
    }

    override fun onPause() {
        super.onPause()
        unregisterReceiver(receiver)
    }

    override fun onDestroy() {
        super.onDestroy()
        ActivityCollector.removeActivity(this)
    }

    inner class ForceOfflineReceiver : BroadcastReceiver() {
        override fun onReceive(context: Context, intent: Intent) {
            AlertDialog.Builder(context).apply {
                setTitle("Warning")
                setMessage("You are forced to be offline. Please try to login again.")
                // 设置为不可取消，避免用户关闭后可以直接继续使用
                setCancelable(false)
                // 使用setPositiveButton()方法给对话框注册确定按钮，当用户点击了“OK”按钮时，就调用ActivityCollector的finishAll()方法销毁所有Activity，并重新启动LoginActivity。
                setPositiveButton("OK") { _, _ ->
                    ActivityCollector.finishAll() // 销毁所有Activity
                    val i = Intent(context, LoginActivity::class.java)
                    context.startActivity(i) // 重新启动LoginActivity
                }
                show()
            }
        }
    }
}