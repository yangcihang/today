package com.hrsoft.today.mvp.contract

import com.hrsoft.today.base.BaseContract
import com.hrsoft.today.mvp.model.SimpleCalendarModel

/**
 * @author YangCihang
 * @since  17/11/5.
 * email yangcihang@hrsoft.net
 */
interface SquareContract {
    interface View : BaseContract.View<Presenter> {
        fun onRecommendCalendarLoadSuccess(calendarModel: List<SimpleCalendarModel>)
        fun onAllCalendarLoadSuccess(calendarModel: List<SimpleCalendarModel>)
        fun onDateLoadFailed()
        fun scrollToLastPage()
    }

    interface Presenter : BaseContract.Presenter<View> {
        fun requestRecommendCalendarList()
        fun requestAllCalendarList(page: Int)
    }
}