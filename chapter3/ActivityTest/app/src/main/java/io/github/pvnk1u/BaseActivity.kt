package io.github.pvnk1u

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity

open class BaseActivity : AppCompatActivity(){

    override fun onCreate(savedInstanceState:Bundle?){
        super.onCreate(savedInstanceState)
        /**
         * 打印当前类名
         */
        Log.d("BaseActivity",javaClass.simpleName)
        /**
         * 将创建的Activity添加到ActivityController中管理
         */
        ActivityController.addActivity(this)
    }

    /**
     * 重写onDestroy方法，实现在销毁Activity的时候将Activity从ActivityController中移除
     */
    override fun onDestroy() {
        super.onDestroy()
        ActivityController.removeActivity(this)
    }
}