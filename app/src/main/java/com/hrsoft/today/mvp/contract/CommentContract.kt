package com.hrsoft.today.mvp.contract

import com.hrsoft.today.base.BaseContract
import com.hrsoft.today.mvp.model.CommentModel

/**
 * @author abtion.
 * @since 17/11/7 00:29.
 * email caiheng@hrsoft.net.
 */
interface CommentContract : BaseContract {
    interface View : BaseContract.View<Presenter> {
        fun onCommentListLoaded(dataList: List<CommentModel>)
        fun onCommentListLoadFailed()
        fun onSendCommentSuccess()
        fun onSendCommentFailed()
        fun onToTheLastPage()
    }

    interface Presenter : BaseContract.Presenter<View> {
        fun getComment(page: Int, calendarId: Int)
        fun onCommentListLoadSuccess(dataList: List<CommentModel>)
        fun onCommentListLoadFailed()
        fun sendComment(id: Int, content: String)
    }
}