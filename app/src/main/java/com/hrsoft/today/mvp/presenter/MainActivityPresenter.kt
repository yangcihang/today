package com.hrsoft.today.mvp.presenter

import com.hrsoft.today.mvp.view.main.MainActivity
import com.hrsoft.today.mvp.contract.MainContract
import com.hrsoft.today.util.ToastUtil

/**
 * @author YangCihang
 * @since  17/9/24.
 * email yangcihang@hrsoft.net
 */
class MainActivityPresenter : MainContract.Presenter {
    override val mView: MainContract.View
        get() = MainActivity()

    override fun requestModel() {
        ToastUtil.showToast("123")
        mView.onDataLoadSuccess()
    }
}