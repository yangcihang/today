package com.hrsoft.today.mvp.contract

import com.hrsoft.today.base.BaseContract
import com.hrsoft.today.mvp.model.models.CalendarModel

/**
 * @author YangCihang
 * @since  17/11/7.
 * email yangcihang@hrsoft.net
 */
interface ManageSubscribedContract {
    interface View : BaseContract.View<Presenter> {
        fun onUnsubscribeSuccess()
        fun onUnsubscribeFailed()
        fun onOrderSuccess()
        fun onOrderFailed()
        fun getCalendarListSuccess(calendarList: List<CalendarModel>)
        fun getCalendarListFailed()
    }

    interface Presenter : BaseContract.Presenter<View> {
        fun unSubscribeCalendar(id: Int)
        fun orderCalendar(calendarList: List<CalendarModel>)
        fun requestCalendarList()
    }
}