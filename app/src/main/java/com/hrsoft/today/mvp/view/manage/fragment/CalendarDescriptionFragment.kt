package com.hrsoft.today.mvp.view.manage.fragment

import android.content.Context
import com.hrsoft.today.R
import com.hrsoft.today.base.BaseFragment
import com.hrsoft.today.mvp.view.manage.activity.CreateCalendarActivity

/**
 * @author YangCihang
 * @since  17/11/6.
 * email yangcihang@hrsoft.net
 */
class CalendarDescriptionFragment : BaseFragment() {
    private var mActivity: CreateCalendarActivity? = null

    override fun getLayoutId(): Int {
        return R.layout.fragment_description
    }

    override fun initVariable() {
    }

    override fun initView() {
    }

    override fun loadData() {
    }

    override fun onAttach(context: Context?) {
        super.onAttach(context)
        mActivity = context as CreateCalendarActivity?
    }
}