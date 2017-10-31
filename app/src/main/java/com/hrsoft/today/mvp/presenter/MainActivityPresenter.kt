package com.hrsoft.today.mvp.presenter

import com.hrsoft.today.mvp.contract.MainContract
import com.hrsoft.today.util.ToastUtil

/**
 * @author YangCihang
 * @since  17/9/24.
 * email yangcihang@hrsoft.net
 */
class MainActivityPresenter(override var mView: MainContract.View?) : MainContract.Presenter {
    override fun onDetach() {
        mView = null
    }


    override fun requestModel() {
        ToastUtil.showToast("123")
        mView!!.onDataLoadSuccess()
    }
}