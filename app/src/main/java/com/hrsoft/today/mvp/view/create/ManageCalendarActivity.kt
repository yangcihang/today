package com.hrsoft.today.mvp.view.create

import com.hrsoft.today.R
import com.hrsoft.today.base.NoBarActivity
import com.hrsoft.today.mvp.contract.CreateContract
import com.hrsoft.today.mvp.presenter.CreateCalendarActivityPresenter

/**
 * @author YangCihang
 * @since  17/11/6.
 * email yangcihang@hrsoft.net
 */
class ManageCalendarActivity : NoBarActivity(), CreateContract.View {
    override var mPresenter: CreateContract.Presenter? = CreateCalendarActivityPresenter(this)

    override fun initVariable() {
    }

    override fun initView() {
    }

    override fun loadData() {
    }

    override fun getLayoutId(): Int {
        return R.layout.activity_create_calendar
    }

    override fun onDestroy() {
        super.onDestroy()
        mPresenter?.onDetach()
    }
}