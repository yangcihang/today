package com.hrsoft.today.mvp.presenter

import com.hrsoft.today.mvp.contract.MainContract
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
        ToastUtil.showToast("123")
    }
}