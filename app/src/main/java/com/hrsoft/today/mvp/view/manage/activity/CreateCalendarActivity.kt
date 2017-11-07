package com.hrsoft.today.mvp.view.manage.activity

import com.hrsoft.today.R
import com.hrsoft.today.base.ToolbarActivity
import com.hrsoft.today.mvp.contract.CreateContract
import com.hrsoft.today.mvp.presenter.CreateCalendarActivityPresenter
import com.hrsoft.today.mvp.view.manage.fragment.CalendarDescriptionFragment
import com.hrsoft.today.mvp.view.manage.fragment.RecommendFragment
import com.hrsoft.today.mvp.view.manage.fragment.StateFragment

/**
 * @author YangCihang
 * @since  17/11/6.
 * email yangcihang@hrsoft.net
 */
class CreateCalendarActivity : ToolbarActivity(), CreateContract.View {
    override var mPresenter: CreateContract.Presenter? = CreateCalendarActivityPresenter(this)
    private var descriptionFragment: CalendarDescriptionFragment? = null
    private var recommendFragment: RecommendFragment? = null
    private var stateFragment: StateFragment? = null
    override fun initVariable() {
    }

    override fun initView() {
    }

    override fun loadData() {
    }

    override fun getLayoutId(): Int {
        return R.layout.activity_create_calendar
    }

    override fun onCreateNewCalendarSuccess() {

    }

    override fun onCreateStateModelSuccess() {
    }

    override fun onCreateNewCalendarFailed() {
    }

    override fun onCreateStateModelFailed() {
    }

    override fun onDestroy() {
        super.onDestroy()
        mPresenter?.onDetach()
    }

}