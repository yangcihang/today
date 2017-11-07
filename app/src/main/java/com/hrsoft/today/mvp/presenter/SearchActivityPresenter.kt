package com.hrsoft.today.mvp.presenter

import com.hrsoft.today.mvp.contract.SearchContract
import com.hrsoft.today.mvp.model.SimpleCalendarModel
import com.hrsoft.today.mvp.model.helper.SearchModelHelper

/**
 * @author YangCihang
 * @since  17/11/5.
 * email yangcihang@hrsoft.net
 */
class SearchActivityPresenter(override var mView: SearchContract.View?) : SearchContract.Presenter {

    override fun requestSearchList(content: String, page: Int) {
        SearchModelHelper.requestSearchModelList(content, page, this)
    }

    override fun onDetach() {
        mView = null
    }

    fun onSearchModelListLoadSuccess(modelList: List<SimpleCalendarModel>) {
        if (modelList.isEmpty()) mView?.scrollToLastPage() else mView?.onSearchListLoadSuccess(modelList)
    }

    fun onSearchModelListLoadFailed() {
        mView?.onSearchListLoadFailed()
    }
}