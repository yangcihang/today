package com.hrsoft.today.mvp.contract

import com.hrsoft.today.base.BaseContract
import com.hrsoft.today.mvp.model.models.MineUserModel

/**
 * @author YangCihang.
 * @since 17/11/24 16:29.
 * email yangcihang@hrsoft.net.
 */
interface MineUpdateContract {
    interface View : BaseContract.View<Presenter> {
        fun updatePswSuccess()
        fun updatePswFailed()
    }

    interface Presenter : BaseContract.Presenter<View> {
        fun updatePsw(model: MineUserModel)
        fun updatePswSuccess()
        fun updatePswFailed()
    }
}