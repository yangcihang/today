package com.hrsoft.today.mvp.model.helper

import com.hrsoft.today.mvp.model.SquareCalendarModel
import com.hrsoft.today.mvp.presenter.SearchActivityPresenter
import com.hrsoft.today.network.NetWork
import com.hrsoft.today.network.RspCallback

/**
 * @author YangCihang
 * @since  17/11/5.
 * email yangcihang@hrsoft.net
 */
object SearchModelHelper {
    fun requestSearchModelList(content: String, callback: SearchActivityPresenter) {
        NetWork.getService().requestSearchModelList(content).enqueue(object : RspCallback<List<SquareCalendarModel>>() {
            override fun onSuccess(data: List<SquareCalendarModel>) {
                callback.onSearchModelListLoadSuccess(data)
            }

            override fun onFailed() {
                callback.onSearchModelListLoadFailed()
            }

        })
    }
}