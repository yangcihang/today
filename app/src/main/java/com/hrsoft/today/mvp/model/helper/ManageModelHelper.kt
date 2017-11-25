package com.hrsoft.today.mvp.model.helper

import com.hrsoft.today.mvp.model.models.CalendarModel
import com.hrsoft.today.mvp.model.models.SimpleCalendarModel
import com.hrsoft.today.mvp.presenter.ManageCreatedFragmentPresenter
import com.hrsoft.today.mvp.presenter.ManageSubscribedPresenter
import com.hrsoft.today.network.NetWork
import com.hrsoft.today.network.RspCallback

/**
 * @author YangCihang
 * @since  17/11/6.
 * email yangcihang@hrsoft.net
 */
object ManageModelHelper {
    fun requestCreatedList(callback: ManageCreatedFragmentPresenter) {
        NetWork.getService().getCreatedCalendar().enqueue(object : RspCallback<List<SimpleCalendarModel>>() {
            override fun onSuccess(data: List<SimpleCalendarModel>?) {
                callback.onCreatedCalendarLoadSuccess(data!!)
            }

            override fun onFailed() {
                callback.onCreatedCalendarLoadFailed()
            }

        })
    }

    fun requestDeleteCreatedModel(id: Int, callback: ManageCreatedFragmentPresenter) {
        NetWork.getService().requestDeleteCreatedCalendar(id).enqueue(object : RspCallback<Long>() {
            override fun onSuccess(data: Long?) {
                callback.deleteCreatedCalendarSuccess()
            }

            override fun onFailed() {
                callback.deleteCreatedCalendarFailed()
            }

        })
    }

    fun unSubscribedCalendar(callback: ManageSubscribedPresenter, id: Int) {
        NetWork.getService().unsubscribeCalendar(id).enqueue(object : RspCallback<Unit>() {
            override fun onSuccess(data: Unit?) {
                callback.onUnsubscribeSuccess()
            }

            override fun onFailed() {
                callback.onUnsubscribeFailed()
            }

        })
    }

    /**
     * 黄历排序
     */
    fun orderCalendar(callback: ManageSubscribedPresenter, calendarList: List<CalendarModel>) {
        NetWork.getService().orderCalendar(calendarList).enqueue(object : RspCallback<Unit>() {
            override fun onSuccess(data: Unit?) {
                callback.onOrderSuccess()
            }

            override fun onFailed() {
                callback.onOrderFailed()
            }

        })
    }

    /**
     * 获取列表
     */
    fun requestCalendarModel(callback: ManageSubscribedPresenter) {
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