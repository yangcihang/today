package com.hrsoft.today.mvp.view.square.activity

import android.support.v4.widget.NestedScrollView
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.LinearLayoutManager
import com.hrsoft.today.R
import com.hrsoft.today.base.NoBarActivity
import com.hrsoft.today.mvp.contract.SquareContract
import com.hrsoft.today.mvp.model.SquareCalendarModel
import com.hrsoft.today.mvp.presenter.SquareActivityPresenter
import com.hrsoft.today.mvp.view.detail.CalendarDetailActivity
import com.hrsoft.today.mvp.view.square.adapter.SquareListAdapter
import com.hrsoft.today.util.ToastUtil
import kotlinx.android.synthetic.main.activity_square.*

/**
 * @author YangCihang
 * @since  17/11/4.
 * email yangcihang@hrsoft.net
 */
class SquareActivity : NoBarActivity(), SquareContract.View {
    override var mPresenter: SquareContract.Presenter? = SquareActivityPresenter(this)
    private var recommendAdapter: SquareListAdapter? = null
    private var allAdapter: SquareListAdapter? = null
    private var page: Int = 1
    private var isLastPage: Boolean = false

    override fun initVariable() {
        recommendAdapter = SquareListAdapter(this).apply {
            onClickedListener = {
                model, _ ->
                CalendarDetailActivity.start(this@SquareActivity, model)
            }
        }
        allAdapter = SquareListAdapter(this).apply {
            onClickedListener = {
                model, _ ->
                CalendarDetailActivity.start(this@SquareActivity, model)
            }
        }
    }

    override fun initView() {
        rec_square_recommend.apply {
            adapter = recommendAdapter
            layoutManager = LinearLayoutManager(this@SquareActivity)
                    .apply { orientation = LinearLayoutManager.HORIZONTAL }
        }

        rec_square_all.apply {
            isNestedScrollingEnabled = false
            adapter = allAdapter
            layoutManager = GridLayoutManager(this@SquareActivity, 3)
        }
        //监听scrollView的下滑事件
        nest_scroll_square.setOnScrollChangeListener {
            v: NestedScrollView?, _: Int, scrollY: Int, _: Int, _: Int ->
            if (scrollY == (v!!.getChildAt(0).measuredHeight - v.measuredHeight)) {
                if (!isLastPage) mPresenter?.requestAllCalendarList(page++)
            }
        }
    }

    override fun loadData() {
        mPresenter?.requestAllCalendarList(page++)
        mPresenter?.requestRecommendCalendarList()
    }

    override fun getLayoutId(): Int {
        return R.layout.activity_square
    }


    override fun onRecommendCalendarLoadSuccess(calendarModel: List<SquareCalendarModel>) {
        recommendAdapter?.addAll(calendarModel)
    }

    override fun onAllCalendarLoadSuccess(calendarModel: List<SquareCalendarModel>) {
        allAdapter?.addAll(calendarModel)
    }

    override fun onDateLoadFailed() {

    }

    override fun scrollToLastPage() {
        isLastPage = true
        ToastUtil.showToast(getString(R.string.toast_is_last_page))
    }

    override fun onDestroy() {
        super.onDestroy()
        mPresenter?.onDetach()
    }
}