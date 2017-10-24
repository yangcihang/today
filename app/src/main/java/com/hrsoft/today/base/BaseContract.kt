package com.hrsoft.today.base


/**
 * @author YangCihang
 * @since  17/9/24.
 * email yangcihang@hrsoft.net
 */
interface BaseContract {
    /**
     * 进行双向绑定
     */
    interface View<out T> {
        val mPresenter:T

    }

    interface Presenter<out T> {
        val mView: T
    }
}
