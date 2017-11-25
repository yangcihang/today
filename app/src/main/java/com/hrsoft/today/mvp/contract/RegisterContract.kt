package com.hrsoft.today.mvp.contract

import com.hrsoft.today.base.BaseContract
import com.hrsoft.today.mvp.model.models.RegisterRequestModel

/**
 * @author YangCihang.
 * @since 17/11/16 22:39.
 * email yangcihang@hrsoft.net.
 */
interface RegisterContract {
    interface View : BaseContract.View<Presenter> {
        fun registerSuccess()
        fun registerFailed()
    }

    interface Presenter : BaseContract.Presenter<View> {
        fun register(registerModel: RegisterRequestModel)
    }
}