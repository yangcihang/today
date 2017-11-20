package com.hrsoft.today.mvp.contract

import com.hrsoft.today.base.BaseContract
import com.hrsoft.today.mvp.model.LoginRequestModel

/**
 * @author YangCihang.
 * @since 17/11/16 22:42.
 * email yangcihang@hrsoft.net.
 */
interface LoginContract {
    interface View : BaseContract.View<Presenter> {
        fun loginSuccess()
        fun loginFailed()
    }

    interface Presenter : BaseContract.Presenter<View> {
        fun login(model: LoginRequestModel)
    }
}