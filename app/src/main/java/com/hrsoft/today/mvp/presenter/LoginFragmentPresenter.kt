package com.hrsoft.today.mvp.presenter

import android.text.TextUtils
import com.hrsoft.today.App
import com.hrsoft.today.R
import com.hrsoft.today.common.Config
import com.hrsoft.today.mvp.contract.LoginContract
import com.hrsoft.today.mvp.model.LoginRequestModel
import com.hrsoft.today.mvp.model.LoginResponseModel
import com.hrsoft.today.mvp.model.User
import com.hrsoft.today.mvp.model.helper.AccountHelper
import com.hrsoft.today.util.ToastUtil

/**
 * @author YangCihang.
 * @since 17/11/16 22:41.
 * email yangcihang@hrsoft.net.
 */
class LoginFragmentPresenter(override var mView: LoginContract.View?) : LoginContract.Presenter {

    override fun login(model: LoginRequestModel) {
        AccountHelper.login(this, model.apply {
            when {
                TextUtils.isEmpty(name) -> {
                    ToastUtil.showToast(R.string.toast_login_name_empty)
                    mView?.loginFailed()
                    return
                }
                (password.length !in 6..20) -> {
                    ToastUtil.showToast(R.string.toast_password_error)
                    mView?.loginFailed()
                    return
                }
            }
        })
    }

    override fun onDetach() {
        mView = null
    }

    fun onLoginSuccess(model: LoginResponseModel) {
        //存储用户信息
        model.user.apply {
            User.avatar = avatar
            User.name = name
            User.signature = signature
            User.token = model.token
        }
        User.saveUserInfo()
        mView?.loginSuccess()
    }

    fun onLoginFailed() {
        mView?.loginFailed()
    }
}