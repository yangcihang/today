package com.hrsoft.today.mvp.view.main.activity

import android.content.Intent
import android.support.v4.view.ViewPager
import android.view.Gravity
import com.hrsoft.today.App
import com.hrsoft.today.R
import com.hrsoft.today.base.NoBarActivity
import com.hrsoft.today.common.Config
import com.hrsoft.today.mvp.contract.MainContract
import com.hrsoft.today.mvp.model.CalendarModel
import com.hrsoft.today.mvp.model.User
import com.hrsoft.today.mvp.presenter.MainActivityPresenter
import com.hrsoft.today.mvp.view.main.adapter.MainPagerAdapter
import com.hrsoft.today.mvp.view.main.fragment.MainContentFragment
import com.hrsoft.today.mvp.view.manage.activity.ManageCalendarActivity
import com.hrsoft.today.mvp.view.square.activity.SquareActivity
import kotlinx.android.synthetic.main.activity_main.*
import kotlin.collections.ArrayList

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
        img_drawer_menu.setOnClickListener { drawer_main.openDrawer(Gravity.START) }
        nv_menu_left.setNavigationItemSelectedListener { item ->
            when (item.itemId) {
                R.id.menu_square -> startActivity(Intent(this@MainActivity, SquareActivity::class.java))
                R.id.menu_create -> startActivity(Intent(this@MainActivity, ManageCalendarActivity::class.java))
            }
            return@setNavigationItemSelectedListener true
        }
        User.userCalendarList.forEach { fragmentList.add(MainContentFragment.createFragment(it)) }
    }

    override fun loadData() {
        mPresenter!!.requestCalendar()
        vp_main.apply {
            adapter?.notifyDataSetChanged()
            adapter = this@MainActivity.adapter
            addOnPageChangeListener(object : ViewPager.OnPageChangeListener {
                override fun onPageScrollStateChanged(state: Int) {
                }

                override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) {
                }

                override fun onPageSelected(position: Int) {
                    txt_calendar_title.text = User.userCalendarList[position].calendarName ?: "默认title"
                }
            })
        }
    }

    override fun getLayoutId(): Int {
        return R.layout.activity_main
    }


    /**
     * 数据获取成功时回调
     */
    override fun onCalendarLoadSuccess(calendarList: List<CalendarModel>) {
        //TODO(没有内容时加入提示)
        User.userCalendarList = calendarList as MutableList<CalendarModel>
        App.instance.getCacheUtil().putSerializableObj(Config.KEY_CALENDAR, User.userCalendarList as ArrayList)
        fragmentList.clear()
        User.userCalendarList.forEach { fragmentList.add(MainContentFragment.createFragment(it)) }
        adapter.notifyDataSetChanged()
        txt_calendar_title.text = User.userCalendarList[0].calendarName ?: "默认title"

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

    override fun onDestroy() {
        super.onDestroy()
        mPresenter?.onDetach()
    }
}
