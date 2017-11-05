package com.hrsoft.today.mvp.presenter

import com.hrsoft.today.mvp.contract.SearchContract

/**
 * @author YangCihang
 * @since  17/11/5.
 * email yangcihang@hrsoft.net
 */
class SearchActivityPresenter(override var mView: SearchContract.View?) : SearchContract.Presenter {
    override fun onDetach() {
        mView = null
    }
}