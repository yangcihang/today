package com.hrsoft.today.mvp.view.manage.fragment

import com.hrsoft.today.R
import com.hrsoft.today.base.BaseFragment
import com.hrsoft.today.mvp.contract.ManageSubscribedContract
import com.hrsoft.today.mvp.presenter.ManageSubscribedPresenter

/**
 * @author YangCihang
 * @since  17/11/7.
 * email yangcihang@hrsoft.net
 */
class SubscribedFragment : BaseFragment(), ManageSubscribedContract.View {
    override var mPresenter: ManageSubscribedContract.Presenter? = ManageSubscribedPresenter(this)

    override fun getLayoutId(): Int {
        return R.layout.fragment_subscribed
    }

    override fun initVariable() {
    }

    override fun initView() {
    }

    override fun loadData() {
    }
}