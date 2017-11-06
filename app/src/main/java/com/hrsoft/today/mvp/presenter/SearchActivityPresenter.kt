package com.hrsoft.today.mvp.presenter

import com.hrsoft.today.mvp.contract.SearchContract
import com.hrsoft.today.mvp.model.SquareCalendarModel
import com.hrsoft.today.mvp.model.helper.SearchModelHelper

/**
 * @author YangCihang
 * @since  17/11/5.
 * email yangcihang@hrsoft.net
 */
class SearchActivityPresenter(override var mView: SearchContract.View?) : SearchContract.Presenter {

    override fun requestSearchList(content: String) {
        SearchModelHelper.requestSearchModelList(content,this)
    }

    override fun onDetach() {
        mView = null
    }

    fun onSearchModelListLoadSuccess(modelList: List<SquareCalendarModel>) {
        mView?.onSearchListLoadSuccess(modelList)
    }

    fun onSearchModelListLoadFailed() {
        mView?.onSearchListLoadFailed()
    }
}