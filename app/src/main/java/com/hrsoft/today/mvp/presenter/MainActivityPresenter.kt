package com.hrsoft.today.mvp.presenter

import com.hrsoft.today.mvp.contract.MainContract
import com.hrsoft.today.mvp.model.models.CalendarModel
import com.hrsoft.today.mvp.model.helper.MainModelHelper
import com.hrsoft.today.util.ToastUtil

/**
 * @author YangCihang
 * @since  17/9/24.
 * email yangcihang@hrsoft.net
 */
class MainActivityPresenter(override var mView: MainContract.View?) : MainContract.Presenter {
    /**
     * 解除绑定
     */
    override fun onDetach() {
        mView = null
    }

    override fun requestCalendar() {
        MainModelHelper.requestCalendarModel(this)
    }

    override fun onCalendarLoadSuccess(calendarList: List<CalendarModel>?) {
        if (calendarList != null) {
            mView?.onCalendarLoadSuccess(calendarList)
        } else {
            ToastUtil.showToast("请求失败")
        }
    }

    override fun onCalendarLoadFailed() {
        mView?.onCalendarLoadFailed()
        ToastUtil.showToast("请求失败")
    }

}