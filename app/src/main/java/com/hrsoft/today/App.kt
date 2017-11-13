package com.hrsoft.today

import android.app.Activity
import android.app.Application
import android.os.Bundle
import com.hrsoft.today.util.CacheUtil
import com.qiniu.android.common.FixedZone
import com.qiniu.android.storage.Configuration
import com.qiniu.android.storage.UploadManager


/**
 * @author YangCihang
 * @since  17/9/23.
 * email yangcihang@hrsoft.net
 */
class App : Application() {

    lateinit var cachePath: String
    lateinit var uploadManager: UploadManager

    companion object {
        lateinit var instance: App
            private set//实例对象
        private lateinit var cacheUtil: CacheUtil

    }

    init {
        instance = this
    }

    private val activityList = ArrayList<Activity>()

    override fun onCreate() {
        super.onCreate()
        cachePath = externalCacheDir.absolutePath
        cacheUtil = CacheUtil.get(filesDir)
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
        val config = Configuration.Builder()
                .chunkSize(512 * 1024)        // 分片上传时，每片的大小。 默认256K
                .putThreshhold(1024 * 1024)   // 启用分片上传阀值。默认512K
                .connectTimeout(10)           // 链接超时。默认10秒
                .useHttps(true)               // 是否使用https上传域名
                .responseTimeout(60)          // 服务器响应超时。默认60秒
                .zone(FixedZone.zone1)        // 设置区域，指定不同区域的上传域名、备用域名、备用IP。
                .build()
        uploadManager = UploadManager(config)
    }

    /**
     * 缓存初始化
     */
    fun getCacheUtil(): CacheUtil {
        return cacheUtil
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

