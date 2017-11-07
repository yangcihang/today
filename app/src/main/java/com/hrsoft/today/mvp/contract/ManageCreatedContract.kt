package com.hrsoft.today.mvp.contract

import com.hrsoft.today.base.BaseContract
import com.hrsoft.today.mvp.model.SimpleCalendarModel

/**
 * @author YangCihang
 * @since  17/11/6.
 * email yangcihang@hrsoft.net
 */
interface ManageCreatedContract {
    interface View : BaseContract.View<Presenter> {
        fun onCalendarLoadSuccess(dataList: List<SimpleCalendarModel>)
        fun onDeleteCalendarSuccess()
        fun onDeleteCalendarFailed()
    }

    interface Presenter : BaseContract.Presenter<View> {
        fun requestCreatedCalendar()
        fun deleteCreatedCalendar(id: Int)
    }
}