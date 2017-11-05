package com.hrsoft.today.mvp.contract

import com.hrsoft.today.base.BaseContract

/**
 * @author YangCihang
 * @since  17/11/5.
 * email yangcihang@hrsoft.net
 */
class SearchContract {
    interface View:BaseContract.View<Presenter> {

    }
    interface Presenter:BaseContract.Presenter<View> {

    }
}