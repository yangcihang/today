package com.hrsoft.today.mvp.contract

import com.hrsoft.today.base.BaseContract
import com.hrsoft.today.mvp.model.SquareCalendarModel

/**
 * @author YangCihang
 * @since  17/11/5.
 * email yangcihang@hrsoft.net
 */
class SearchContract {
    interface View : BaseContract.View<Presenter> {
        fun onSearchListLoadSuccess(modelList: List<SquareCalendarModel>)
        fun onSearchListLoadFailed()

    }

    interface Presenter : BaseContract.Presenter<View> {
        fun requestSearchList(content: String)
    }
}