package com.hrsoft.today.mvp.model.helper

import com.hrsoft.today.mvp.model.CalendarDetailModel
import com.hrsoft.today.mvp.model.CommentModel
import com.hrsoft.today.mvp.presenter.CommentFragmentPresenter
import com.hrsoft.today.mvp.presenter.DetailActivityPresenter
import com.hrsoft.today.network.NetWork
import com.hrsoft.today.network.RspCallback

/**
 * @author abtion.
 * @since 17/11/7 00:37.
 * email caiheng@hrsoft.net.
 */
object DetailModelHelper {
    fun requestCalendarCommentList(page: Int, calendarId: Int, callback: CommentFragmentPresenter) {
        NetWork.getService().requestCalendarCommentList(calendarId, page).enqueue(object :
                RspCallback<List<CommentModel>>() {
            override fun onSuccess(data: List<CommentModel>?) {
                callback.onCommentListLoadSuccess(data!!)
            }

            override fun onFailed() {
                callback.onCommentListLoadFailed()
            }
        })
    }

    fun requestCalendarInfo(calendarId: Int, callback: DetailActivityPresenter) {
        NetWork.getService().requestCalendarDetail(calendarId).enqueue(object : RspCallback<CalendarDetailModel>() {
            override fun onSuccess(data: CalendarDetailModel?) {
                callback.onDetailLoaded(data!!)
            }

            override fun onFailed() {
                callback.onDetailLoadFailed()
            }

        })

    }
}