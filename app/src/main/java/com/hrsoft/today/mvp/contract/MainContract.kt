package com.hrsoft.today.mvp.contract

import com.hrsoft.today.base.BaseContract

/**
 * @author YangCihang
 * @since  17/9/24.
 * email yangcihang@hrsoft.net
 */
interface MainContract {
    interface View : BaseContract.View<Presenter> {
        fun onDataLoadSuccess()
    }

    interface Presenter : BaseContract.Presenter<View> {
        fun requestModel()
    }
}