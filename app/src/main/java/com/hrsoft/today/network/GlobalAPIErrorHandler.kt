package com.hrsoft.today.network

import com.hrsoft.today.App
import com.hrsoft.today.util.ToastUtil


/**
 * @author YangCihang
 * @since  17/9/28.
 * email yangcihang@hrsoft.net
 */
object GlobalAPIErrorHandler {
    fun handle(code: Int): Unit {
        when (code) {
            200 -> ToastUtil.showToast("200")
            400 -> ToastUtil.showToast("400")
            500 -> ToastUtil.showToast("服务器崩了")
            20001 -> App.instance.toLogin()
        }
    }
}