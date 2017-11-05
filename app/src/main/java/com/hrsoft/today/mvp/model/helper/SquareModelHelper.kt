package com.hrsoft.today.mvp.model.helper

import com.hrsoft.today.mvp.model.SquareCalendarModel
import com.hrsoft.today.mvp.presenter.SquareActivityPresenter
import com.hrsoft.today.network.NetWork
import com.hrsoft.today.network.RspCallback

/**
 * @author YangCihang
 * @since  17/11/5.
 * email yangcihang@hrsoft.net
 */
object SquareModelHelper {
    fun requestRecommendCalendar(callback: SquareActivityPresenter) {
        NetWork.getService().requestSquareRecommendCalendarList().enqueue(object : RspCallback<List<SquareCalendarModel>>() {
            override fun onSuccess(data: List<SquareCalendarModel>) {
                callback.onRecommendCalendarLoadSuccess(data)
            }

            override fun onFailed() {
                callback.onDataLoadFailed()
            }

        })

    }

    fun requestAllCalendar(page:Int,callback: SquareActivityPresenter) {
        NetWork.getService().requestSquareAllCalendarList(page).enqueue(object : RspCallback<List<SquareCalendarModel>>() {
            override fun onSuccess(data: List<SquareCalendarModel>) {
                callback.onAllCalendarLoadSuccess(data)
            }

            override fun onFailed() {
                callback.onDataLoadFailed()
            }
        })
    }

}