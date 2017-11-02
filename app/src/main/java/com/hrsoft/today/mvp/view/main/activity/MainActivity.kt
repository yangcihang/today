package com.hrsoft.today.mvp.view.main.activity

import com.hrsoft.today.R
import com.hrsoft.today.base.NoBarActivity
import com.hrsoft.today.mvp.contract.MainContract
import com.hrsoft.today.mvp.model.CalendarModel
import com.hrsoft.today.mvp.model.User
import com.hrsoft.today.mvp.presenter.MainActivityPresenter
import com.hrsoft.today.mvp.view.main.adapter.MainPagerAdapter
import com.hrsoft.today.mvp.view.main.fragment.MainContentFragment
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : NoBarActivity(), MainContract.View {

    /**presenter*/
    override var mPresenter: MainContract.Presenter? = MainActivityPresenter(this)
    /**fragmentList*/
    private var fragmentList: MutableList<MainContentFragment> = mutableListOf()
    /**adapter*/
    private var adapter: MainPagerAdapter = MainPagerAdapter(supportFragmentManager, fragmentList)

    override fun initVariable() {
    }

    override fun initView() {
        vp_main.adapter = adapter
//        User.userCalendarList.forEach { fragmentList.add(MainContentFragment.createFragment(it)) }
        fragmentList.add(MainContentFragment.createFragment(null))
        fragmentList.add(MainContentFragment.createFragment(null))
        fragmentList.add(MainContentFragment.createFragment(null))
        adapter.notifyDataSetChanged()
    }

    override fun loadData() {
        mPresenter!!.requestCalendar()
    }

    override fun getLayoutId(): Int {
        return R.layout.activity_main
    }


    /**
     * 数据获取成功时回调
     */
    override fun onCalendarLoadSuccess(calendarList: MutableList<CalendarModel>) {
        User.userCalendarList = calendarList
        fragmentList.clear()
        calendarList.forEach { fragmentList.add(MainContentFragment.createFragment(it)) }
        adapter.notifyDataSetChanged()
    }

    /**
     * 数据返回失败回调
     */
    override fun onCalendarLoadFailed() {
    }

    override fun onRestart() {
        super.onRestart()
        mPresenter!!.requestCalendar()
    }
}
