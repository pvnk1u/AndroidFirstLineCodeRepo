package io.github.pvnk1u

import android.app.NotificationManager
import android.content.Context
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity

class NotificationActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_notification)
        /**
         * 设置加载后关闭相应的状态栏通知显示
         */
        val manager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        // 这里的1就是创建通知时通知的相应编号
        manager.cancel(1)
    }
}