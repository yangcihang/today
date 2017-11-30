package com.hrsoft.today.mvp.presenter

import com.hrsoft.today.mvp.contract.ManageSubscribedContract
import com.hrsoft.today.mvp.model.models.CalendarModel
import com.hrsoft.today.mvp.model.helper.ManageModelHelper
import com.hrsoft.today.util.ToastUtil

/**
 * @author YangCihang
 * @since  17/11/7.
 * email yangcihang@hrsoft.net
 */
class ManageSubscribedPresenter(override var mView: ManageSubscribedContract.View?) : ManageSubscribedContract.Presenter {
    override fun requestCalendarList() {
        ManageModelHelper.requestCalendarModel(this)
    }

    override fun orderCalendar(calendarList: List<CalendarModel>) {
        ManageModelHelper.orderCalendar(this, calendarList)
    }

    override fun unSubscribeCalendar(id: Int) {
        ManageModelHelper.unSubscribedCalendar(this, id)
    }

    override fun onDetach() {
        mView = null
    }

    fun onUnsubscribeSuccess() {
        mView?.onUnsubscribeSuccess()
    }

    fun onUnsubscribeFailed() {
        mView?.onUnsubscribeFailed()
    }

    fun onOrderSuccess() {
        mView?.onOrderSuccess()
    }

    fun onOrderFailed() {
        mView?.onOrderFailed()
    }

    fun onCalendarLoadSuccess(calendarList: List<CalendarModel>?) {
        if (calendarList != null) {
            mView?.getCalendarListSuccess(calendarList)
        } else {
            ToastUtil.showToast("请求失败")
        }
    }

    fun onCalendarLoadFailed() {
        ToastUtil.showToast("请求失败")
    }

}