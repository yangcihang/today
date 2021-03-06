package com.hrsoft.today.mvp.presenter

import com.hrsoft.today.mvp.contract.DetailContract
import com.hrsoft.today.mvp.model.models.CalendarDetailModel
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
        DetailModelHelper.requestCalendarInfo(calendarId, this)
    }


    override fun subscribeCalendar(id: Int) {
        DetailModelHelper.subscribedCalendar(this, id)
    }

    override fun unSubscribeCalendar(id: Int) {
        DetailModelHelper.unSubscribedCalendar(this, id)
    }

    override fun onDetailLoaded(mData: CalendarDetailModel) {
        mView?.onDetailLoaded(mData)
    }

    override fun onDetailLoadFailed() {
        mView?.onDetailLoadFailed()
    }

    fun onSubscribeSuccess() {
        mView?.onSubscribeSuccess()
    }

    fun onSubscribeFailed() {
        mView?.onSubscribeFailed()
    }

    fun onUnsubscribeSuccess() {
        mView?.onUnsubscribeSuccess()
    }

    fun onUnsubscribeFailed() {
        mView?.onUnsubscribeFailed()
    }
}