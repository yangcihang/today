package com.hrsoft.today.mvp.presenter

import com.hrsoft.today.mvp.contract.CommentFragmentContrat
import com.hrsoft.today.mvp.model.helper.DetailModelHelper
import com.hrsoft.today.mvp.model.CommentModel

/**
 * @author abtion.
 * @since 17/11/7 00:31.
 * email caiheng@hrsoft.net.
 */
class CommentFragmentPresenter(override var mView: CommentFragmentContrat.View?) :CommentFragmentContrat.Presenter {
    override fun onDetach() {
        mView = null
    }

    override fun onCommentListLoaded(dataList: List<CommentModel>) {
        mView?.onCommentListLoaded(dataList)
    }

    override fun onCommentListLoadFailed() {
        mView?.onCommentListLoadFailed()
    }



    override fun getComment(calendarId: Int) {
        DetailModelHelper.requestCalendarCommentList(calendarId,this)
    }
}