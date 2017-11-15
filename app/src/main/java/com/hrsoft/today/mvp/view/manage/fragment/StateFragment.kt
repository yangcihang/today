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
    var stateList: MutableList<CalendarStateItemModel> = mutableListOf()
    override fun initVariable() {
        adapter = CreatedStateAdapter(context)
        adapter.addAll(stateList)
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
        }
        btn_state_save.setOnClickListener {
            val titleContent = edit_state_title.text.toString().trim()
            val badContent = edit_state_bad.text.toString().trim()
            val goodContent = edit_state_good.text.toString().trim()
            when {
                (TextUtils.isEmpty(titleContent) || titleContent.length < 4 || titleContent.length > 20) -> {
                    ToastUtil.showToast("请输入4-20位的标题")
                    return@setOnClickListener
                }
                (TextUtils.isEmpty(goodContent)) -> {
                    ToastUtil.showToast("请输入Good内容")
                    return@setOnClickListener
                }
                (TextUtils.isEmpty(badContent)) -> {
                    ToastUtil.showToast("请输入Bad内容")
                    return@setOnClickListener
                }
            }
            ToastUtil.showToast("保存成功")
            stateList.add(CalendarStateItemModel(name = titleContent, good = goodContent, bad = badContent))
            adapter.add(CalendarStateItemModel(name = titleContent, good = goodContent, bad = badContent))
        }
    }

    override fun loadData() {
    }

    override fun getLayoutId(): Int {
        return R.layout.fragment_state
    }

}