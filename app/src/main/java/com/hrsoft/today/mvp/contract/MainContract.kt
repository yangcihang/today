package com.hrsoft.today.mvp.contract

import com.hrsoft.today.base.BaseContract
import com.hrsoft.today.mvp.model.CalendarModel

/**
 * @author YangCihang
 * @since  17/9/24.
 * email yangcihang@hrsoft.net
 */
interface MainContract {
    interface View : BaseContract.View<Presenter> {
        fun onCalendarLoadSuccess(calendarList: List<CalendarModel>)
        fun onCalendarLoadFailed()
    }

    interface Presenter : BaseContract.Presenter<View> {
        fun requestCalendar()
        //TODO("这块到时候会改为list")
        fun onCalendarLoadSuccess(calendarList: List<CalendarModel>?)

        fun onCalendarLoadFailed()
    }
}