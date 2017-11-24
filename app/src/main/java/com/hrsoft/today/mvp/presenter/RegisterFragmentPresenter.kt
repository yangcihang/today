package com.hrsoft.today.mvp.presenter

import com.hrsoft.today.R
import com.hrsoft.today.mvp.contract.RegisterContract
import com.hrsoft.today.mvp.model.RegisterRequestModel
import com.hrsoft.today.mvp.model.helper.AccountHelper
import com.hrsoft.today.util.ToastUtil

/**
 * @author YangCihang.
 * @since 17/11/16 22:40.
 * email yangcihang@hrsoft.net.
 */
class RegisterFragmentPresenter(override var mView: RegisterContract.View?) : RegisterContract.Presenter {

    override fun onDetach() {
        mView = null
    }

    override fun register(registerModel: RegisterRequestModel) {
        AccountHelper.register(this, registerModel.apply {
            when {
                (name.length !in 3..20) -> {
                    ToastUtil.showToast(R.string.toast_nickname_error)
                    mView?.registerFailed()
                    return
                }
                (password.length !in 6..20) -> {
                    ToastUtil.showToast(R.string.toast_password_error)
                    mView?.registerFailed()
                    return
                }
                (confirmPassword != password) -> {
                    ToastUtil.showToast(R.string.toast_password_confirm_error)
                    mView?.registerFailed()
                    return
                }
            }
        })
    }

    fun onRegisterSuccess() {
        mView?.registerSuccess()
    }

    fun onRegisterFailed() {
        mView?.registerFailed()
    }
}