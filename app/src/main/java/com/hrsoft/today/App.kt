package com.hrsoft.today

import android.app.Activity
import android.app.Application
import android.os.Bundle

/**
 * @author YangCihang
 * @since  17/9/23.
 * email yangcihang@hrsoft.net
 */
class App : Application() {

    companion object {
        lateinit var instance: App
            private set//实例对象
    }

    init {
        instance = this
    }

    private val activityList = ArrayList<Activity>()

    override fun onCreate() {
        super.onCreate()
        registerActivityLifecycleCallbacks(object : ActivityLifecycleCallbacks {
            override fun onActivityPaused(p0: Activity?) {
            }

            override fun onActivityResumed(p0: Activity?) {
            }

            override fun onActivityStarted(p0: Activity?) {
            }

            override fun onActivityDestroyed(p0: Activity?) {
                activityList.remove(p0)
            }

            override fun onActivitySaveInstanceState(p0: Activity?, p1: Bundle?) {
            }

            override fun onActivityStopped(p0: Activity?) {
            }

            override fun onActivityCreated(p0: Activity?, p1: Bundle?) {
                p0?.let { activityList.add(it) }
            }

        })
    }

    /**
     * 清除所有Activity
     */
    fun removeAllActivity() {
        activityList
                .filterNot { it.isFinishing }
                .forEach { it.finish() }
    }

    /**
     * 退出应用
     */
    fun exitApp() {
        removeAllActivity()
        // TODO: 17/8/25 退出的后续操作
    }



    /**
     * 移除Activity

     * @param activity
     */
    fun removeActivity(activity: Activity?) {
        if (activity != null && !activity.isFinishing) {
            activity.finish()
        }
    }
}

