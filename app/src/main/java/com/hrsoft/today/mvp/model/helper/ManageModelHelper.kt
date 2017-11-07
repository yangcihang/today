package com.hrsoft.today.mvp.model.helper

import com.hrsoft.today.mvp.model.SimpleCalendarModel
import com.hrsoft.today.mvp.presenter.ManageCalendarActivityPresenter
import com.hrsoft.today.network.NetWork
import com.hrsoft.today.network.RspCallback

/**
 * @author YangCihang
 * @since  17/11/6.
 * email yangcihang@hrsoft.net
 */
object ManageModelHelper {
    fun requestCreatedList(callback: ManageCalendarActivityPresenter) {
        NetWork.getService().getCreatedCalendar().enqueue(object : RspCallback<List<SimpleCalendarModel>>() {
            override fun onSuccess(data: List<SimpleCalendarModel>) {
                callback.onCreatedCalendarLoadSuccess(data)
            }

            override fun onFailed() {
                callback.onCreatedCalendarLoadFailed()
            }

        })
    }
}