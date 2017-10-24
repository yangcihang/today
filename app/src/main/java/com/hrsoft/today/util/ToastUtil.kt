package com.hrsoft.today.util

import android.support.annotation.StringRes
import android.widget.Toast
import com.hrsoft.today.App

/**
 * @author YangCihang
 * @since  17/9/23.
 * email yangcihang@hrsoft.net
 */
object ToastUtil {
    private val duration = Toast.LENGTH_SHORT
    private val isShowErrorCode = true
    fun showToast(msg: String) {
        Utility.runOnUiThread(Runnable { Toast.makeText(App.instance, msg, duration).show() })
    }

    fun showToast(@StringRes resId: Int) {
        Utility.runOnUiThread(Runnable { Toast.makeText(App.instance, resId, duration).show() })
    }

    fun showToast(msg: String, vararg errorCode: Int) {
        var message = msg
        if (isShowErrorCode) {
            for (anErrorCode in errorCode) {
                message = message + "-" + anErrorCode
            }
        }
        showToast(message)
    }

    fun showToast(@StringRes resId: Int, vararg errorCode: Int) {
        var msg = App.instance.resources.getString(resId)
        if (isShowErrorCode) {
            for (anErrorCode in errorCode) {
                msg = msg + "-" + anErrorCode
            }
        }
        showToast(msg)
    }
}
