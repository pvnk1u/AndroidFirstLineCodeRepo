package io.github.pvnk1u

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.widget.Toast

/**
 * 自定义广播
 */
class MyBroadcastReceiver : BroadcastReceiver() {

    override fun onReceive(context: Context, intent: Intent) {
        Toast.makeText(context,"received in MyBroadcastReceiver",
            Toast.LENGTH_SHORT).show()
    }
}