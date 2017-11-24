package com.hrsoft.today.mvp.presenter

import com.hrsoft.today.mvp.contract.MineFragmentContract
import com.hrsoft.today.mvp.model.helper.MineModelHelper

/**
 * @author YangCihang.
 * @since 17/11/23 21:39.
 * email yangcihang@hrsoft.net.
 */
class MineFragmentPresenter(override var mView: MineFragmentContract.View?) : MineFragmentContract.Presenter {
    override fun onDetach() {
        mView = null
    }

    override fun updateSign(model: String) {
        MineModelHelper.updateUserSign(this, model)
    }

    override fun updateSignSuccess() {
        mView?.updateSignSuccess()
    }

    override fun updateSignFailed() {
        mView?.updateSignFailed()
    }
}