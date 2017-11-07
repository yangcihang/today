package com.hrsoft.today.mvp.view.manage.activity

import android.support.v7.widget.LinearLayoutManager
import com.hrsoft.today.R
import com.hrsoft.today.base.ToolbarActivity
import com.hrsoft.today.mvp.contract.ManageContract
import com.hrsoft.today.mvp.model.SimpleCalendarModel
import com.hrsoft.today.mvp.presenter.ManageCalendarActivityPresenter
import com.hrsoft.today.mvp.view.manage.adapter.ManageAdapter
import kotlinx.android.synthetic.main.activity_manage_calendar.*

/**
 * @author YangCihang
 * @since  17/11/6.
 * email yangcihang@hrsoft.net
 */
class ManageCalendarActivity : ToolbarActivity(), ManageContract.View {

    override var mPresenter: ManageContract.Presenter? = ManageCalendarActivityPresenter(this)
    private lateinit var adapter: ManageAdapter

    override fun initVariable() {
        adapter = ManageAdapter(this)
    }

    override fun initView() {
        setActivityTitle(getString(R.string.text_manage_calendar))
        rec_manage_calendar.apply {
            layoutManager = LinearLayoutManager(this@ManageCalendarActivity)
            adapter = this@ManageCalendarActivity.adapter
        }
    }

    override fun loadData() {
        mPresenter?.requestCreatedCalendar()
    }

    override fun getLayoutId(): Int {
        return R.layout.activity_manage_calendar
    }

    override fun onCalendarLoadSuccess(dataList: List<SimpleCalendarModel>) {
        adapter.addAll(dataList)
    }

    override fun onDestroy() {
        super.onDestroy()
        mPresenter?.onDetach()
    }
}