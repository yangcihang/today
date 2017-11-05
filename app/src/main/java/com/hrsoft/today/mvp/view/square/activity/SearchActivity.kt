package com.hrsoft.today.mvp.view.square.activity

import com.hrsoft.today.R
import com.hrsoft.today.base.NoBarActivity
import com.hrsoft.today.mvp.contract.SearchContract
import com.hrsoft.today.mvp.presenter.SearchActivityPresenter

/**
 * @author YangCihang
 * @since  17/11/5.
 * email yangcihang@hrsoft.net
 */
class SearchActivity : NoBarActivity(), SearchContract.View {
    override var mPresenter: SearchContract.Presenter? = SearchActivityPresenter(this)

    override fun initVariable() {
    }

    override fun initView() {
    }

    override fun loadData() {
    }

    override fun getLayoutId(): Int {
        return R.layout.activity_search
    }
}