package com.hrsoft.today.mvp.presenter

import com.hrsoft.today.mvp.contract.DetailContract

/**
 * @author YangCihang
 * @since  17/11/5.
 * email yangcihang@hrsoft.net
 */
class DetailActivityPresenter(override var mView: DetailContract.View?) : DetailContract.Presenter {
    override fun onDetach() {
        mView = null
    }

}