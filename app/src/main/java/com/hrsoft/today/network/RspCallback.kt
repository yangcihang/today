package com.hrsoft.today.network

import com.hrsoft.today.util.ToastUtil
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.net.ConnectException

/**
 * 网络请求响应的封装
 * @author YangCihang
 * @since  17/9/25.
 * email yangcihang@hrsoft.net
 */
abstract class RspCallback<T> : Callback<RspModel<T>> {
    abstract fun onSuccess(data: T?)
    abstract fun onFailed()
    override fun onResponse(call: Call<RspModel<T>>?, response: Response<RspModel<T>>) {
        if (response.code() <= 400) {
            if (response.body()!!.code == 0) {
                onSuccess(response.body()!!.data)
            } else {
                response.body().message?.let { ToastUtil.showToast(it) }
                GlobalAPIErrorHandler.handle(response.body()!!.code)
            }
        } else {
            GlobalAPIErrorHandler.handle(response.code())
            onFailed()
        }
    }

    override fun onFailure(call: Call<RspModel<T>>?, t: Throwable?) {
        onFailed()
        when (t) {
            is ConnectException -> ToastUtil.showToast("网络错误")
            else -> ToastUtil.showToast("未知错误")
        }
    }
}