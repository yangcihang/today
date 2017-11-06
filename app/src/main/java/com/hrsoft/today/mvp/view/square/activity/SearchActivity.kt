package com.hrsoft.today.mvp.view.square.activity

import android.support.v7.widget.LinearLayoutManager
import android.view.inputmethod.EditorInfo
import com.hrsoft.today.R
import com.hrsoft.today.base.NoBarActivity
import com.hrsoft.today.mvp.contract.SearchContract
import com.hrsoft.today.mvp.model.SquareCalendarModel
import com.hrsoft.today.mvp.presenter.SearchActivityPresenter
import com.hrsoft.today.mvp.view.detail.activity.CalendarDetailActivity
import com.hrsoft.today.mvp.view.square.adapter.SearchListAdapter
import kotlinx.android.synthetic.main.activity_search.*


/**
 * @author YangCihang
 * @since  17/11/5.
 * email yangcihang@hrsoft.net
 */
class SearchActivity : NoBarActivity(), SearchContract.View {

    override var mPresenter: SearchContract.Presenter? = SearchActivityPresenter(this)
    private lateinit var adapter: SearchListAdapter
    override fun initVariable() {
        adapter = SearchListAdapter(this).apply {
            onClickedListener = {
                model, _ ->
                CalendarDetailActivity.start(this@SearchActivity, model)
            }
        }
    }

    override fun initView() {
        img_back.setOnClickListener { this.finish() }
        rec_search.apply {
            adapter = this@SearchActivity.adapter
            layoutManager = LinearLayoutManager(this@SearchActivity)
        }
        img_search.setOnClickListener { mPresenter?.requestSearchList(edit_search.text.toString().trim()) }
        edit_search.setOnEditorActionListener { _, actionId, _ ->
            if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                mPresenter?.requestSearchList(edit_search.text.toString().trim())
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

    override fun onSearchListLoadSuccess(modelList: List<SquareCalendarModel>) {
        adapter.refreshData(modelList)
    }


    override fun onDestroy() {
        super.onDestroy()
        mPresenter?.onDetach()
    }
}