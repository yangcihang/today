package com.hrsoft.today.mvp.view.square.activity

import android.support.v7.widget.LinearLayoutManager
import android.view.inputmethod.EditorInfo
import com.hrsoft.today.R
import com.hrsoft.today.base.NoBarActivity
import com.hrsoft.today.base.RecyclerScrollListener
import com.hrsoft.today.mvp.contract.SearchContract
import com.hrsoft.today.mvp.model.models.SimpleCalendarModel
import com.hrsoft.today.mvp.presenter.SearchActivityPresenter
import com.hrsoft.today.mvp.view.detail.activity.CalendarDetailActivity
import com.hrsoft.today.mvp.view.square.adapter.SearchListAdapter
import com.hrsoft.today.util.ToastUtil
import kotlinx.android.synthetic.main.activity_search.*


/**
 * @author YangCihang
 * @since  17/11/5.
 * email yangcihang@hrsoft.net
 */
class SearchActivity : NoBarActivity(), SearchContract.View {
    private var page = 1
    private var isLastPage = false
    override var mPresenter: SearchContract.Presenter? = SearchActivityPresenter(this)
    private lateinit var adapter: SearchListAdapter
    override fun initVariable() {
        adapter = SearchListAdapter(this).apply {
            onClickedListener = { model, _ ->
                CalendarDetailActivity.start(this@SearchActivity, model)
            }
        }
    }

    override fun initView() {
        img_back.setOnClickListener { this.finish() }
        rec_search.apply {
            adapter = this@SearchActivity.adapter
            layoutManager = LinearLayoutManager(this@SearchActivity)
            addOnScrollListener(RecyclerScrollListener(
                    { if (!isLastPage) mPresenter?.requestSearchList(edit_search.text.toString().trim(), page++) }))
        }
        img_search.setOnClickListener {
            isLastPage = false
            page = 1
            adapter.dataList.clear()
            mPresenter?.requestSearchList(edit_search.text.toString().trim(), page++)
        }
        edit_search.setOnEditorActionListener { _, actionId, _ ->
            if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                isLastPage = false
                page = 1
                adapter.dataList.clear()
                mPresenter?.requestSearchList(edit_search.text.toString().trim(), page++)
                return@setOnEditorActionListener true
            }
            return@setOnEditorActionListener false
        }
    }

    override fun loadData() {
    }

    override fun getLayoutId(): Int {
        return R.layout.activity_search
    }

    override fun onSearchListLoadFailed() {
    }

    override fun scrollToLastPage() {
        ToastUtil.showToast("已经到底啦~")
        isLastPage = true
    }

    override fun onSearchListLoadSuccess(modelList: List<SimpleCalendarModel>) {
        adapter.addAll(modelList)
    }


    override fun onDestroy() {
        super.onDestroy()
        mPresenter?.onDetach()
    }
}