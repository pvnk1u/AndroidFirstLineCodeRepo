package io.github.pvnk1u

import android.app.Activity

/**
 * 这里使用了单例类，是因为全局只需要一个Activity集合
 */
object ActivityController {

    private val activities = ArrayList<Activity>()

    /**
     * 添加Activity
     */
    fun addActivity(activity: Activity){
        activities.add(activity)
    }

    /**
     * 移除Activity
     */
    fun removeActivity(activity: Activity){
        activities.remove(activity)
    }

    /**
     * 结束所有Activity
     */
    fun finishAll(){
        for (activity in activities){
            if (!activity.isFinishing){
                activity.finish()
            }
        }
        activities.clear()
    }
}