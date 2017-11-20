package com.hrsoft.today.mvp.view.detail.fragment

import android.os.Bundle
import android.text.TextUtils
import com.hrsoft.today.R
import com.hrsoft.today.base.BaseFragment
import com.hrsoft.today.common.Config
import com.hrsoft.today.mvp.model.CalendarDetailModel
import kotlinx.android.synthetic.main.fragment_calendar_description.*

/**
 * @author abtion.
 * @since 17/11/6 20:09.
 * email caiheng@hrsoft.net.
 */
class DescriptionFragment : BaseFragment() {

    private var calendarModel: CalendarDetailModel? = null

    companion object {
        /**
         * 静态启动
         */
        fun createFragment(calendar: CalendarDetailModel?): DescriptionFragment {
            val bundle = Bundle()
            bundle.putParcelable(Config.KEY_CALENDAR_DETAIL, calendar)
            return DescriptionFragment().apply { arguments = bundle }
        }
    }

    override fun getLayoutId(): Int {
        return R.layout.fragment_calendar_description
    }

    override fun initVariable() {
        calendarModel = arguments.getParcelable(Config.KEY_CALENDAR_DETAIL)
    }

    override fun initView() {
        txt_description.text =
                if (!TextUtils.isEmpty(calendarModel?.description)) calendarModel?.description else "暂无描述"

    }

    override fun loadData() {
    }

}