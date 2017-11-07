package com.hrsoft.today.mvp.view.detail.fragment

import com.hrsoft.today.R
import com.hrsoft.today.base.BaseFragment
import com.hrsoft.today.mvp.contract.DetailContract
import com.hrsoft.today.mvp.model.CalendarDetailModel
import com.hrsoft.today.mvp.presenter.DetailActivityPresenter
import kotlinx.android.synthetic.main.fragment_calendar_description.*

/**
 * @author abtion.
 * @since 17/11/6 20:09.
 * email caiheng@hrsoft.net.
 */
class DescriptionFragment : BaseFragment(), DetailContract.View {


    override var mPresenter: DetailContract.Presenter? = DetailActivityPresenter(this)
    override fun getLayoutId(): Int {
        return R.layout.fragment_calendar_description
    }

    override fun initVariable() {

    }

    override fun initView() {
    }

    override fun loadData() {
    }

    override fun onDetailLoaded(mData: CalendarDetailModel) {
        txt_description.text = mData.description
    }

    override fun onDetailLoadFailed() {
    }

}