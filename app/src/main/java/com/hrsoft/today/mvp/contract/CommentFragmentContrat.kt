package com.hrsoft.today.mvp.contract

import com.hrsoft.today.base.BaseContract
import com.hrsoft.today.mvp.model.CommentModel

/**
 * @author abtion.
 * @since 17/11/7 00:29.
 * email caiheng@hrsoft.net.
 */
class CommentFragmentContrat:BaseContract{
    interface View : BaseContract.View<Presenter> {
        fun onCommentListLoaded(dataList:List<CommentModel>)
        fun onCommentListLoadFailed()
    }

    interface Presenter : BaseContract.Presenter<View> {
        fun getComment(calendarId:Int)
        fun onCommentListLoaded(dataList:List<CommentModel>)
        fun onCommentListLoadFailed()
    }
}