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

    /**
     * onCreate()方法其实也有一个Bundle类型的参数。这个参数在一般情况下都是
     * null，但是如果在Activity被系统回收之前，通过onSaveInstanceState()方法保存了数
     * 据，这个参数就会带有之前保存的全部数据，只需要再通过相应的取值方法将数据取出即可。
     */
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d(tag,"onCreate")
        setContentView(R.layout.activity_main)
        /**
         * 取出在当前Activity被回收前通过onSaveInstanceState方法向Bundle对象中保存的数据
          */
        if (savedInstanceState != null){
            val tempData = savedInstanceState.getString("data_key")
            Log.d(tag,"tempData is $tempData")
        }

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


    /**
     * 当一个Activity进入了停止状态，是有可能被系统回收的。那么想象以下场景：
     * 应用中有一个Activity A，用户在Activity A的基础上启动了Activity B，Activity A就进入了
     * 停止状态，这个时候由于系统内存不足，将Activity A回收掉了，然后用户按下Back键返回
     * Activity A，会出现什么情况呢？其实还是会正常显示Activity A的，只不过这时并不会执行
     * onRestart()方法，而是会执行Activity A的onCreate()方法，因为Activity A在这种情况
     * 下会被重新创建一次。
     *
     * 这样看上去好像一切正常，可是别忽略了一个重要问题：Activity A中是可能存在临时数据和状
     * 态的。打个比方，MainActivity中如果有一个文本输入框，现在你输入了一段文字，然后启动
     * NormalActivity，这时MainActivity由于系统内存不足被回收掉，过了一会你又点击了Back键
     * 回到MainActivity，你会发现刚刚输入的文字都没了，因为MainActivity被重新创建了。
     *
     * 如果我们的应用出现了这种情况，是会比较影响用户体验的，所以得想想办法解决这个问题。
     * 其实，Activity中还提供了一个onSaveInstanceState()回调方法，这个方法可以保证在
     * Activity被回收之前一定会被调用，因此我们可以通过这个方法来解决问题。
     */
    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        val tempData = "Something you just typed"
        outState.putString("data_key",tempData)
    }
}