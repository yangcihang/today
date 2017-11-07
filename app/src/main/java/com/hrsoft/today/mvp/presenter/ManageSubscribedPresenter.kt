package com.hrsoft.today.mvp.presenter

import com.hrsoft.today.mvp.contract.ManageSubscribedContract

/**
 * @author YangCihang
 * @since  17/11/7.
 * email yangcihang@hrsoft.net
 */
class ManageSubscribedPresenter(override var mView: ManageSubscribedContract.View?) : ManageSubscribedContract.Presenter {
    override fun onDetach() {
        mView = null
    }

}