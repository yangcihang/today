package com.hrsoft.today.mvp.contract

import com.hrsoft.today.base.BaseContract

/**
 * @author YangCihang.
 * @since 17/11/23 21:39.
 * email yangcihang@hrsoft.net.
 */
interface MineFragmentContract {
    interface View : BaseContract.View<Presenter> {
        fun updateSignSuccess()
        fun updateSignFailed()
    }

    interface Presenter : BaseContract.Presenter<View> {
        fun updateSign(model: String)
        fun updateSignSuccess()
        fun updateSignFailed()
    }
}