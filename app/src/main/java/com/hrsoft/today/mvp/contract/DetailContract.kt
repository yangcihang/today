package com.hrsoft.today.mvp.contract

import com.hrsoft.today.base.BaseContract
import com.hrsoft.today.mvp.model.models.CalendarDetailModel

/**
 * @author YangCihang
 * @since  17/11/5.
 * email yangcihang@hrsoft.net
 */
interface DetailContract {
    interface View : BaseContract.View<Presenter> {
        fun onDetailLoaded(mData: CalendarDetailModel)
        fun onDetailLoadFailed()

        fun onSubscribeSuccess()
        fun onSubscribeFailed()
        fun onUnsubscribeSuccess()
        fun onUnsubscribeFailed()
    }

    interface Presenter : BaseContract.Presenter<View> {
        fun getCalendarInfo(calendarId: Int)

        fun onDetailLoaded(mData: CalendarDetailModel)
        fun onDetailLoadFailed()
        fun subscribeCalendar(id: Int)
        fun unSubscribeCalendar(id: Int)
    }
}