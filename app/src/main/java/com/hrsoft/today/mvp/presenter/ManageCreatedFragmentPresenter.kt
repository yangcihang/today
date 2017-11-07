package com.hrsoft.today.mvp.presenter

import com.hrsoft.today.mvp.contract.ManageCreatedContract
import com.hrsoft.today.mvp.model.SimpleCalendarModel
import com.hrsoft.today.mvp.model.helper.ManageModelHelper

/**
 * @author YangCihang
 * @since  17/11/6.
 * email yangcihang@hrsoft.net
 */
class ManageCreatedFragmentPresenter(override var mView: ManageCreatedContract.View?) : ManageCreatedContract.Presenter {
    override fun requestCreatedCalendar() {
        ManageModelHelper.requestCreatedList(this)

    }

    override fun deleteCreatedCalendar(id: Int) {
        ManageModelHelper.requestDeleteCreatedModel(id,this)
    }

    fun deleteCreatedCalendarSuccess() {
        mView?.onDeleteCalendarSuccess()
    }

    fun deleteCreatedCalendarFailed() {
        mView?.onDeleteCalendarFailed()
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