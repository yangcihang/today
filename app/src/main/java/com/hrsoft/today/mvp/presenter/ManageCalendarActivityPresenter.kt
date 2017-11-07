package com.hrsoft.today.mvp.presenter

import com.hrsoft.today.mvp.contract.ManageContract
import com.hrsoft.today.mvp.model.SimpleCalendarModel
import com.hrsoft.today.mvp.model.helper.ManageModelHelper

/**
 * @author YangCihang
 * @since  17/11/6.
 * email yangcihang@hrsoft.net
 */
class ManageCalendarActivityPresenter(override var mView: ManageContract.View?) : ManageContract.Presenter {
    override fun requestCreatedCalendar() {
        ManageModelHelper.requestCreatedList(this)

    }

    fun onCreatedCalendarLoadSuccess(dataList: List<SimpleCalendarModel>) {
        mView?.onCalendarLoadSuccess(dataList)
    }

    fun onCreatedCalendarLoadFailed() {

    }


    override fun onDetach() {
        mView = null
    }
}