package io.github.pvnk1u

import android.content.Intent
import android.os.Bundle
import android.widget.Button

class MainActivity : BaseActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val forceOffline : Button = findViewById(R.id.forceOffline)
        forceOffline.setOnClickListener{
            val intent  = Intent("io.github.pvnk1u.broadcastbestpractice.FORCE_OFFLINE")
            intent.setPackage(packageName)
            sendBroadcast(intent)
        }
    }
}