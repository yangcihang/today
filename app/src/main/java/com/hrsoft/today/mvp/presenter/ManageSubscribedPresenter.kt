package com.hrsoft.today.mvp.presenter

import com.hrsoft.today.mvp.contract.ManageSubscribedContract
import com.hrsoft.today.mvp.model.helper.ManageModelHelper

/**
 * @author YangCihang
 * @since  17/11/7.
 * email yangcihang@hrsoft.net
 */
class ManageSubscribedPresenter(override var mView: ManageSubscribedContract.View?) : ManageSubscribedContract.Presenter {
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
}