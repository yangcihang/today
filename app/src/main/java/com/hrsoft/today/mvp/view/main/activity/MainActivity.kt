package com.hrsoft.today.mvp.view.main.activity

import android.content.Intent
import android.support.v4.view.ViewPager
import android.text.TextUtils
import android.view.Gravity
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide
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
import com.hrsoft.today.util.ToastUtil
import jp.wasabeef.glide.transformations.CropCircleTransformation
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
        initNavigation()
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
        if (calendarList.isEmpty()) {
            ToastUtil.showToast(R.string.toast_calendar_empty)
            startActivity(Intent(this, SquareActivity::class.java))
            return
        }
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

    /**
     * 初始化navigation
     */
    private fun initNavigation() {
        nv_menu_left.apply {
            setNavigationItemSelectedListener { item ->
                when (item.itemId) {
                    R.id.menu_square -> startActivity(Intent(this@MainActivity, SquareActivity::class.java))
                    R.id.menu_create -> startActivity(Intent(this@MainActivity, ManageCalendarActivity::class.java))
                    R.id.menu_exit -> App.instance.toLogin()
                }
                return@setNavigationItemSelectedListener true
            }
            //TODO（用户信息）
            inflateHeaderView(R.layout.view_navigation_head).let {
                it.findViewById<TextView>(R.id.txt_user_name).text = User.name
                it.findViewById<TextView>(R.id.txt_user_sign).text =
                        if (TextUtils.isEmpty(User.signature)) "您还没有设置签名" else User.signature
                //TODO(加入placeHolder)
                Glide.with(this@MainActivity)
                        .load(User.avatar)
                        .bitmapTransform(CropCircleTransformation(this@MainActivity))
                        .error(R.drawable.ic_default_avatar)
                        .into(it.findViewById(R.id.img_user_avatar))
            }
        }
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
