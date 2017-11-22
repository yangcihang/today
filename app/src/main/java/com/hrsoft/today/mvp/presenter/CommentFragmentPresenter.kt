package com.hrsoft.today.mvp.presenter

import android.text.TextUtils
import android.widget.Toast
import com.hrsoft.today.mvp.contract.CommentContract
import com.hrsoft.today.mvp.model.CommentModel
import com.hrsoft.today.mvp.model.helper.DetailModelHelper
import com.hrsoft.today.util.ToastUtil

/**
 * @author abtion.
 * @since 17/11/7 00:31.
 * email caiheng@hrsoft.net.
 */
class CommentFragmentPresenter(override var mView: CommentContract.View?) : CommentContract.Presenter {
    override fun sendComment(id: Int, content: String) {
        if (TextUtils.isEmpty(content)) {
            return
        } else {
            DetailModelHelper.sendComment(this, id, content)
        }
    }

    override fun onDetach() {
        mView = null
    }

    override fun onCommentListLoadSuccess(dataList: List<CommentModel>) {
        if (dataList.isEmpty()) mView?.onToTheLastPage() else mView?.onCommentListLoaded(dataList)
    }

    override fun onCommentListLoadFailed() {
        mView?.onCommentListLoadFailed()
    }


    override fun getComment(page: Int, calendarId: Int) {
        DetailModelHelper.requestCalendarCommentList(page, calendarId, this)
    }

    fun onSendCommentSuccess() {
        mView?.onSendCommentSuccess()
    }

    fun onSendCommentFailed() {
        mView?.onSendCommentFailed()
    }
}