package com.hrsoft.today.mvp.view.detail.fragment

import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import com.hrsoft.today.R
import com.hrsoft.today.base.BaseFragment
import com.hrsoft.today.common.Config
import com.hrsoft.today.mvp.model.models.CalendarDetailModel
import com.hrsoft.today.mvp.view.main.adapter.RecommendListAdapter
import com.hrsoft.today.mvp.view.main.adapter.StateListAdapter
import kotlinx.android.synthetic.main.fragment_main_content.*

/**
 * @author abtion.
 * @since 17/11/6 20:09.
 * email caiheng@hrsoft.net.
 */
class PreviewFragment : BaseFragment() {
    private lateinit var stateGoodAdapter: StateListAdapter
    private lateinit var stateBadAdapter: StateListAdapter
    private lateinit var recommendAdapter: RecommendListAdapter
    private var calendarDetailModel: CalendarDetailModel? = null

    companion object {
        /**
         * 静态启动
         */
        fun createFragment(calendar: CalendarDetailModel?): PreviewFragment {
            val bundle = Bundle()
            bundle.putSerializable(Config.KEY_CALENDAR_DETAIL, calendar)
            return PreviewFragment().apply { arguments = bundle }
        }
    }

    override fun getLayoutId(): Int {
        return R.layout.fragment_calendar_preview
    }

    override fun initVariable() {
        stateGoodAdapter = StateListAdapter(context, true)
        stateBadAdapter = StateListAdapter(context, false)
        recommendAdapter = RecommendListAdapter(context)
        calendarDetailModel = arguments.getSerializable(Config.KEY_CALENDAR_DETAIL) as CalendarDetailModel?
    }

    override fun initView() {
        rec_calendar_good.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = stateGoodAdapter
            isNestedScrollingEnabled = false
        }
        rec_calendar_bad.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = stateBadAdapter
            isNestedScrollingEnabled = false
        }
        rec_calendar_recommend.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = recommendAdapter
            isNestedScrollingEnabled = false
        }
    }

    override fun loadData() {
    }

}