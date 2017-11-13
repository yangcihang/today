package com.hrsoft.today.mvp.presenter

import com.hrsoft.today.mvp.contract.CommentContract
import com.hrsoft.today.mvp.model.CommentModel
import com.hrsoft.today.mvp.model.helper.DetailModelHelper

/**
 * @author abtion.
 * @since 17/11/7 00:31.
 * email caiheng@hrsoft.net.
 */
class CommentFragmentPresenter(override var mView: CommentContract.View?) : CommentContract.Presenter {
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
}