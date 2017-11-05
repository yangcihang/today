package com.hrsoft.today.mvp.presenter

import com.hrsoft.today.mvp.contract.SquareContract
import com.hrsoft.today.mvp.model.SquareCalendarModel
import com.hrsoft.today.mvp.model.helper.SquareModelHelper

/**
 * @author YangCihang
 * @since  17/11/5.
 * email yangcihang@hrsoft.net
 */
class SquareActivityPresenter(override var mView: SquareContract.View?) : SquareContract.Presenter {
    override fun onDetach() {
        mView = null
    }

    override fun requestRecommendCalendarList() {
        SquareModelHelper.requestRecommendCalendar(this)
    }

    override fun requestAllCalendarList(page: Int) {
        SquareModelHelper.requestAllCalendar(page, this)
    }

    fun onRecommendCalendarLoadSuccess(calendarList: List<SquareCalendarModel>) {
        mView?.onRecommendCalendarLoadSuccess(calendarList)
    }

    fun onAllCalendarLoadSuccess(calendarList: List<SquareCalendarModel>) {
        if (calendarList.isEmpty()) mView?.scrollToLastPage()
        mView?.onAllCalendarLoadSuccess(calendarList)
    }

    fun onDataLoadFailed() {
        mView?.onDateLoadFailed()
    }
}