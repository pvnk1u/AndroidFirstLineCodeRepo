package io.github.pvnk1u

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

/**
 * 启动项目、第一次创建MainActivity时控制台依次打印：onCreate、onStart、onResume
 *
 * 点击startNormalActivity按钮打开NormalActivity时，MainActivity已经完全被NormalActivity遮挡住，因此控制台会依次打印onPause和onStop，此时MainActivity已经进入了停止状态
 * 然后按下back键返回MainActivity，因为之前MainActivity已经因为打开了NormalActivity而进入了停止状态，所以onRestart()方法会得到执行，之后会依次
 * 执行onStart()和onResume()方法。注意，此时onCreate()方法不会执行，因为MainActivity并没有重新创建。
 *
 * 点击startDialogActivity按钮打开Dialog对话框时，控制台只会打印onPause，不会打印onStop，这是因为
 * DialogActivity并没有完全遮挡住MainActivity，此时MainActivity只是进入了暂停状态，并
 * 没有进入停止状态。相应地，按下Back键返回MainActivity也应该只有onResume()方法会得到执行。
 *
 * 最后在MainActivity按下Back键退出程序，控制台会依次打印：onPause、onStop、onDestroy，最终销毁MainActivity。
 */
class MainActivity : AppCompatActivity() {

    private val tag = "MainActivity"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d(tag,"onCreate")
        setContentView(R.layout.activity_main)
        val startNormalActivity: Button = findViewById(R.id.startNormalActivity)
        val startDialogActivity: Button = findViewById(R.id.startDialogActivity)
        startNormalActivity.setOnClickListener {
            val intent = Intent(this, NormalActivity::class.java)
            startActivity(intent)
        }
        startDialogActivity.setOnClickListener {
            val intent = Intent(this, DialogActivity::class.java);
            startActivity(intent)
        }
    }

    override fun onStart() {
        super.onStart()
        Log.d(tag, "onStart")
    }
    override fun onResume() {
        super.onResume()
        Log.d(tag, "onResume")
    }
    override fun onPause() {
        super.onPause()
        Log.d(tag, "onPause")
    }
    override fun onStop() {
        super.onStop()
        Log.d(tag, "onStop")
    }
    override fun onDestroy() {
        super.onDestroy()
        Log.d(tag, "onDestroy")
    }
    override fun onRestart() {
        super.onRestart()
        Log.d(tag, "onRestart")
    }
}