package com.hrsoft.today.mvp.model.helper

import com.hrsoft.today.mvp.model.CalendarStateItemModel
import com.hrsoft.today.mvp.model.NewCalendarModel
import com.hrsoft.today.mvp.model.NewCalendarRecommendModel
import com.hrsoft.today.mvp.presenter.CreateCalendarActivityPresenter
import com.hrsoft.today.network.NetWork
import com.hrsoft.today.network.RspCallback

/**
 * @author YangCihang
 * @since  17/11/6.
 * email yangcihang@hrsoft.net
 */
object CreateModelHelper {
    fun createNewCalendar(callback: CreateCalendarActivityPresenter, model: NewCalendarModel) {
        NetWork.getService().createNewCalendar(model).enqueue(object : RspCallback<Long>() {
            override fun onSuccess(data: Long?) {
                callback.onCreateNewCalendarSuccess()
            }

            override fun onFailed() {
                callback.onCreateNewCalendarFailed()
            }
        })
    }

    fun createStateModel(callback: CreateCalendarActivityPresenter, id: Int, model: List<CalendarStateItemModel>) {
        NetWork.getService().createCalendarStates(id, model).enqueue(object : RspCallback<Long>() {
            override fun onFailed() {
                callback.onCreateStateModelSuccess()
            }

            override fun onSuccess(data: Long?) {
                callback.onCreateStateModelFailed()
            }

        })
    }

    fun createNewRecommendModel(callback: CreateCalendarActivityPresenter, id: Int, model: List<NewCalendarRecommendModel>) {
        NetWork.getService().createCalendarRecommend(id, model).enqueue(object : RspCallback<Long>() {
            override fun onSuccess(data: Long?) {
                callback.onCreateRecommendSuccess()
            }

            override fun onFailed() {
                callback.onCreateRecommendFailed()
            }

        })
    }
}