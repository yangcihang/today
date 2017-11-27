package com.hrsoft.today.mvp.view.manage.fragment

import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.text.TextUtils
import android.view.View
import com.hrsoft.today.R
import com.hrsoft.today.base.BaseFragment
import com.hrsoft.today.common.Config
import com.hrsoft.today.mvp.contract.CreateStateContract
import com.hrsoft.today.mvp.model.models.CalendarStateItemModel
import com.hrsoft.today.mvp.presenter.CreateStateFragmentPresenter
import com.hrsoft.today.mvp.view.manage.activity.CreateCalendarActivity
import com.hrsoft.today.mvp.view.manage.adapter.CreatedStateAdapter
import com.hrsoft.today.util.ToastUtil
import kotlinx.android.synthetic.main.fragment_state.*

/**
 * @author YangCihang
 * @since  17/11/6.
 * email yangchang@hrsoft.net
 */
class StateFragment : BaseFragment(), CreateStateContract.View {
    override var mPresenter: CreateStateContract.Presenter? = CreateStateFragmentPresenter(this)
    private lateinit var adapter: CreatedStateAdapter
    private var calendarId = CreateCalendarActivity.DEFAULT_ID

    companion object {
        /**
         * 静态启动
         */
        fun createFragment(id: Int): StateFragment {
            val bundle = Bundle()
            bundle.putInt(Config.KEY_CALENDAR, id)
            return StateFragment().apply { arguments = bundle }
        }
    }

    override fun initVariable() {
        calendarId = arguments.getInt(Config.KEY_CALENDAR)
        adapter = CreatedStateAdapter(context)
    }

    override fun initView() {
        var isExpand = false

        rec_create_state.apply {
            adapter = this@StateFragment.adapter
            layoutManager = LinearLayoutManager(context)
        }

        ll_expand_layout.setOnClickListener {
            if (isExpand) rec_create_state.visibility = View.GONE
            else rec_create_state.visibility = View.VISIBLE
            isExpand = !isExpand
            img_state_arrow.isSelected = !isExpand
        }

        btn_state_save.setOnClickListener {
            val titleContent = edit_state_title.text.toString().trim()
            val badContent = edit_state_bad.text.toString().trim()
            val goodContent = edit_state_good.text.toString().trim()
            when {
                (TextUtils.isEmpty(titleContent) || titleContent.length !in 4..20) -> {
                    ToastUtil.showToast(R.string.toast_state_title_error)
                    return@setOnClickListener
                }
                (TextUtils.isEmpty(goodContent)) -> {
                    ToastUtil.showToast(R.string.toast_state_good_empty)
                    return@setOnClickListener
                }
                (TextUtils.isEmpty(badContent)) -> {
                    ToastUtil.showToast(R.string.toast_state_bad_empty)
                    return@setOnClickListener
                }
            }
            ToastUtil.showToast(R.string.toast_save_success)
            adapter.add(CalendarStateItemModel(name = titleContent, good = goodContent, bad = badContent))
            edit_state_title.setText("")
            edit_state_good.setText("")
            edit_state_bad.setText("")
        }
    }

    override fun loadData() {
        if (calendarId != CreateCalendarActivity.DEFAULT_ID) {
            showProgressDialog(R.string.dialog_wait)
            mPresenter?.getStateDetail(calendarId)
        }
    }

    override fun getLayoutId(): Int = R.layout.fragment_state

    /**
     * 更新状态的数据
     */
    fun updateStateData() {
        showProgressDialog(R.string.dialog_wait)
        mPresenter?.updateStateModel((context as CreateCalendarActivity).calendarId, adapter.dataList, (context as
                CreateCalendarActivity).stateSum)
    }

    /**
     * 创建状态成功时回调
     */
    override fun updateStateModelSuccess() {
        disMissProgressDialog()
        ToastUtil.showToast("创建状态成功")
        (context as CreateCalendarActivity).changeFragmentCallback?.invoke()
    }

    /**
     * 创建状态失败时回调
     */
    override fun updateStateModelFailed() {
        disMissProgressDialog()
        ToastUtil.showToast("创建状态失败")
    }

    /**
     * 获取黄历状态成功时回调
     */
    override fun onStateLoadSuccess(stateList: List<CalendarStateItemModel>) {
        disMissProgressDialog()
        adapter.addAll(stateList)
    }

    override fun onStateLoadFailed() {
        disMissProgressDialog()
        ToastUtil.showToast("获取黄历状态信息失败")
    }

}