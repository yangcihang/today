package com.hrsoft.today.mvp.view.manage.activity

import android.content.Intent
import com.hrsoft.today.R
import com.hrsoft.today.base.ToolbarActivity
import com.hrsoft.today.mvp.view.manage.adapter.ManagePagerAdapter
import kotlinx.android.synthetic.main.activity_manage_calendar.*

/**
 * @author YangCihang
 * @since  17/11/6.
 * email yangcihang@hrsoft.net
 */
class ManageCalendarActivity : ToolbarActivity() {
    private lateinit var adapter: ManagePagerAdapter
    override fun loadData() {

    }

    override fun initVariable() {
        adapter = ManagePagerAdapter(supportFragmentManager)
    }

    override fun initView() {
        setActivityTitle(getString(R.string.text_manage_calendar))
        tab_manage.setupWithViewPager(vp_manage.apply { adapter = this@ManageCalendarActivity.adapter })
    }


    override fun getLayoutId(): Int {
        return R.layout.activity_manage_calendar
    }

}