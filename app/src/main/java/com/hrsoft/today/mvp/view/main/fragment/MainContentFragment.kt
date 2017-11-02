package com.hrsoft.today.mvp.view.main.fragment

import android.os.Bundle
import com.hrsoft.today.R
import com.hrsoft.today.base.BaseFragment
import com.hrsoft.today.common.Config
import com.hrsoft.today.mvp.model.CalendarModel


/**
 * @author YangCihang
 * @since  17/10/31.
 * email yangcihang@hrsoft.net
 */
class MainContentFragment : BaseFragment() {
    var calendar: CalendarModel? = null

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
//          arguments.get(Config.KEY_CALENDAR)as CalendarModel
    }

    override fun initView() {
    }

    override fun loadData() {
    }
}