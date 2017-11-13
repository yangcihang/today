package com.hrsoft.today.mvp.presenter

import com.hrsoft.today.mvp.contract.DetailContract
import com.hrsoft.today.mvp.model.CalendarDetailModel
import com.hrsoft.today.mvp.model.helper.DetailModelHelper

/**
 * @author YangCihang
 * @since  17/11/5.
 * email yangcihang@hrsoft.net
 */
class DetailActivityPresenter(override var mView: DetailContract.View?) : DetailContract.Presenter {

    override fun onDetach() {
        mView = null
    }

    override fun getCalendarInfo(calendarId: Int) {
        DetailModelHelper.requestCalendarInfo(calendarId,this)
    }

    override fun onDetailLoaded(mData: CalendarDetailModel) {
        mView?.onDetailLoaded(mData)
    }

    override fun onDetailLoadFailed() {
        mView?.onDetailLoadFailed()
    }




}