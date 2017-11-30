package com.hrsoft.today.mvp.model.helper

import com.hrsoft.today.mvp.model.models.CalendarDetailModel
import com.hrsoft.today.mvp.model.models.CommentModel
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

    fun subscribedCalendar(callback: DetailActivityPresenter, id: Int) {
        NetWork.getService().subscribeCalendar(id).enqueue(object : RspCallback<Unit>() {
            override fun onSuccess(data: Unit?) {
                callback.onSubscribeSuccess()
            }

            override fun onFailed() {
                callback.onSubscribeFailed()
            }

        })
    }

    fun unSubscribedCalendar(callback: DetailActivityPresenter, id: Int) {
        NetWork.getService().unsubscribeCalendar(id).enqueue(object : RspCallback<Unit>() {
            override fun onSuccess(data: Unit?) {
                callback.onUnsubscribeSuccess()
            }

            override fun onFailed() {
                callback.onUnsubscribeFailed()
            }
        })
    }

    fun sendComment(callback: CommentFragmentPresenter, id: Int, content: String) {
        NetWork.getService().comment(id, CommentModel().apply { comment = content }).enqueue(object : RspCallback<Unit>() {
            override fun onSuccess(data: Unit?) {
                callback.onSendCommentSuccess()
            }

            override fun onFailed() {
                callback.onSendCommentFailed()
            }
        })
    }
}