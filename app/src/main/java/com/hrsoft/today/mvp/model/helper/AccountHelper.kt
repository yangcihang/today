package com.hrsoft.today.mvp.model.helper

import com.hrsoft.today.mvp.model.models.LoginRequestModel
import com.hrsoft.today.mvp.model.models.LoginResponseModel
import com.hrsoft.today.mvp.model.models.RegisterRequestModel
import com.hrsoft.today.mvp.presenter.LoginFragmentPresenter
import com.hrsoft.today.mvp.presenter.RegisterFragmentPresenter
import com.hrsoft.today.network.NetWork
import com.hrsoft.today.network.RspCallback

/**
 * @author YangCihang.
 * @since 17/11/17 21:54.
 * email yangcihang@hrsoft.net.
 */
object AccountHelper {
    fun login(callback: LoginFragmentPresenter, loginModel: LoginRequestModel) {
        NetWork.getService().login(loginModel).enqueue(object : RspCallback<LoginResponseModel>() {
            override fun onSuccess(data: LoginResponseModel?) {
                callback.onLoginSuccess(data!!)
            }

            override fun onFailed() {
                callback.onLoginFailed()
            }

        })
    }

    fun register(callback: RegisterFragmentPresenter, registerModel: RegisterRequestModel) {
        NetWork.getService().register(registerModel).enqueue(object : RspCallback<Long>() {
            override fun onSuccess(data: Long?) {
                callback.onRegisterSuccess()
            }

            override fun onFailed() {
                callback.onRegisterFailed()
            }
        })
    }
}