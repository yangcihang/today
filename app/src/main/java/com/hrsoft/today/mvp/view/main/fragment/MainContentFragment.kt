package com.hrsoft.today.mvp.view.main.fragment

import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import com.hrsoft.today.R
import com.hrsoft.today.base.BaseFragment
import com.hrsoft.today.common.Config
import com.hrsoft.today.mvp.model.CalendarModel
import com.hrsoft.today.mvp.view.main.adapter.RecommendListAdapter
import com.hrsoft.today.mvp.view.main.adapter.StateListAdapter
import kotlinx.android.synthetic.main.fragment_main_content.*


/**
 * @author YangCihang
 * @since  17/10/31.
 * email yangcihang@hrsoft.net
 */
class MainContentFragment : BaseFragment() {
    private var calendar: CalendarModel? = null
    private lateinit var stateGoodAdapter: StateListAdapter
    private lateinit var stateBadAdapter: StateListAdapter
    private lateinit var recommendAdapter: RecommendListAdapter

    companion object {
        /**
         * 静态启动
         */
        fun createFragment(calendar: CalendarModel?): MainContentFragment {
            val bundle: Bundle = Bundle()
            bundle.putSerializable(Config.KEY_CALENDAR, calendar)
            return MainContentFragment().apply { arguments = bundle }
        }
    }

    override fun getLayoutId(): Int {
        return R.layout.fragment_main_content
    }

    override fun initVariable() {
        calendar = arguments.getSerializable(Config.KEY_CALENDAR) as CalendarModel?
        stateGoodAdapter = StateListAdapter(context, true)
        stateBadAdapter = StateListAdapter(context, false)
        recommendAdapter = RecommendListAdapter(context)
    }

    override fun initView() {
        rec_calendar_good.apply {
            layoutManager = LinearLayoutManager(this@MainContentFragment.context)
            adapter = stateGoodAdapter
            isNestedScrollingEnabled = false
        }
        rec_calendar_bad.apply {
            layoutManager = LinearLayoutManager(this@MainContentFragment.context)
            adapter = stateBadAdapter
            isNestedScrollingEnabled = false
        }
        rec_calendar_recommend.apply {
            layoutManager = LinearLayoutManager(this@MainContentFragment.context)
            adapter = recommendAdapter
            isNestedScrollingEnabled = false
        }
    }

    override fun loadData() {
        calendar?.good?.let { stateGoodAdapter.addAll(it) }
        calendar?.bad?.let { stateBadAdapter.addAll(it) }
    }
}