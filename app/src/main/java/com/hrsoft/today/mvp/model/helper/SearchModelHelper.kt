package com.hrsoft.today.mvp.model.helper

import com.hrsoft.today.mvp.model.SimpleCalendarModel
import com.hrsoft.today.mvp.presenter.SearchActivityPresenter
import com.hrsoft.today.network.NetWork
import com.hrsoft.today.network.RspCallback

/**
 * @author YangCihang
 * @since  17/11/5.
 * email yangcihang@hrsoft.net
 */
object SearchModelHelper {
    fun requestSearchModelList(content: String,page:Int, callback: SearchActivityPresenter) {
        NetWork.getService().requestSearchModelList(content,page).enqueue(object : RspCallback<List<SimpleCalendarModel>>() {
            override fun onSuccess(data: List<SimpleCalendarModel>) {
                callback.onSearchModelListLoadSuccess(data)
            }

            override fun onFailed() {
                callback.onSearchModelListLoadFailed()
            }

        })
    }
}