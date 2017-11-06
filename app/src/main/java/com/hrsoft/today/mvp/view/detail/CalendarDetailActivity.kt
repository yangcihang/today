package com.hrsoft.today.mvp.view.detail

import android.content.Context
import android.content.Intent
import com.hrsoft.today.R
import com.hrsoft.today.base.NoBarActivity
import com.hrsoft.today.common.Config
import com.hrsoft.today.mvp.contract.DetailContract
import com.hrsoft.today.mvp.model.SquareCalendarModel
import com.hrsoft.today.mvp.presenter.DetailActivityPresenter

/**
 * @author YangCihang
 * @since  17/11/5.
 * email yangcihang@hrsoft.net
 */
class CalendarDetailActivity : NoBarActivity(), DetailContract.View {

    companion object {
        fun start(context: Context, model: SquareCalendarModel) {
            context.startActivity(Intent(context, CalendarDetailActivity::class.java).apply {
                putExtra(Config.KEY_SQUARE_CALENDAR, model)
            })
        }
    }

    override var mPresenter: DetailContract.Presenter? = DetailActivityPresenter(this)

    override fun initVariable() {
    }

    override fun initView() {
    }

    override fun loadData() {
    }

    override fun getLayoutId(): Int {
        return R.layout.activity_calendar_detail
    }

    override fun onDestroy() {
        super.onDestroy()
        mPresenter?.onDetach()
    }
}