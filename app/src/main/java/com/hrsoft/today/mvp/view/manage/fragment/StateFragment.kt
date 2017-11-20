package com.hrsoft.today.mvp.view.manage.fragment

import android.support.v7.widget.LinearLayoutManager
import android.text.TextUtils
import android.view.View
import com.hrsoft.today.R
import com.hrsoft.today.base.BaseFragment
import com.hrsoft.today.mvp.model.CalendarStateItemModel
import com.hrsoft.today.mvp.view.manage.adapter.CreatedStateAdapter
import com.hrsoft.today.util.ToastUtil
import kotlinx.android.synthetic.main.fragment_state.*

/**
 * @author YangCihang
 * @since  17/11/6.
 * email yangchang@hrsoft.net
 */
class StateFragment : BaseFragment() {
    private lateinit var adapter: CreatedStateAdapter
    override fun initVariable() {
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
                (TextUtils.isEmpty(titleContent) || titleContent.length < 4 || titleContent.length > 20) -> {
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
        }
    }

    override fun loadData() {
    }

    fun getStateData(): MutableList<CalendarStateItemModel> = adapter.dataList

    override fun getLayoutId(): Int {
        return R.layout.fragment_state
    }

}