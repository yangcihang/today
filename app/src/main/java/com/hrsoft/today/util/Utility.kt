package com.hrsoft.today.util

import android.os.Handler
import android.os.Looper

/**
 * @author YangCihang
 * @since  17/9/23.
 * email yangcihang@hrsoft.net
 */
object Utility {
    private var uiHandle: Handler? = null

    /**
     * 获取UI线程
     */
    private fun getUIHandle() {
        if (uiHandle == null) {
            synchronized(Utility::class.java) {
                if (uiHandle == null) {
                    uiHandle = Handler(Looper.getMainLooper())
                }
            }
        }
    }

    /**
     * UI线程中运行
     */
    @JvmOverloads fun runOnUiThread(runnable: Runnable, delayMills: Long = 0) {
        getUIHandle()
        uiHandle!!.postDelayed(runnable, delayMills)
    }

    /**
     * 在新线程中运行
     */
    fun runOnNewThread(runnable: Runnable) {
        Thread(runnable).start()
    }

}
