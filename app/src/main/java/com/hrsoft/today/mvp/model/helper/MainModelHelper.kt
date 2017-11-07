package com.hrsoft.today.mvp.model.helper

import com.hrsoft.today.mvp.model.CalendarModel
import com.hrsoft.today.mvp.presenter.MainActivityPresenter
import com.hrsoft.today.network.NetWork
import com.hrsoft.today.network.RspCallback

/**
 * @author YangCihang
 * @since  17/9/24.
 * email yangcihang@hrsoft.net
 */
object MainModelHelper {
    fun requestCalendarModel(callback: MainActivityPresenter) {
        NetWork.getService().requestCalendarList().enqueue(object : RspCallback<List<CalendarModel>>() {
            override fun onSuccess(data: List<CalendarModel>?) {
                callback.onCalendarLoadSuccess(data)
            }

            override fun onFailed() {
                callback.onCalendarLoadFailed()
            }

        })
    }
}