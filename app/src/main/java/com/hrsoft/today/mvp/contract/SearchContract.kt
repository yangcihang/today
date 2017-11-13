package com.hrsoft.today.mvp.contract

import com.hrsoft.today.base.BaseContract
import com.hrsoft.today.mvp.model.SimpleCalendarModel

/**
 * @author YangCihang
 * @since  17/11/5.
 * email yangcihang@hrsoft.net
 */
interface SearchContract {
    interface View : BaseContract.View<Presenter> {
        fun onSearchListLoadSuccess(modelList: List<SimpleCalendarModel>)
        fun onSearchListLoadFailed()
        fun scrollToLastPage()


    }

    interface Presenter : BaseContract.Presenter<View> {
        fun requestSearchList(content: String, page: Int)
    }
}