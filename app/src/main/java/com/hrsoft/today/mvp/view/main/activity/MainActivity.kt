package com.hrsoft.today.mvp.view.main.activity

import android.content.Intent
import android.net.Uri
import android.support.v4.view.ViewPager
import android.text.TextUtils
import android.util.Log
import android.view.Gravity
import android.view.View
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
import com.hrsoft.today.mvp.view.manage.activity.ManageCalendarActivity
import com.hrsoft.today.mvp.view.mine.activity.MineActivity
import com.hrsoft.today.mvp.view.square.activity.SquareActivity
import com.hrsoft.today.util.ToastUtil
import com.hrsoft.today.util.Utility
import jp.wasabeef.glide.transformations.BlurTransformation
import jp.wasabeef.glide.transformations.CropCircleTransformation
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.view_navigation_head.view.*
import kotlin.collections.ArrayList

class MainActivity : NoBarActivity(), MainContract.View {

    /**presenter*/
    override var mPresenter: MainContract.Presenter? = MainActivityPresenter(this)
    /**adapter*/
    private var adapter: MainPagerAdapter = MainPagerAdapter(supportFragmentManager, User.userCalendarList)
    private var headView: View? = null

    override fun initVariable() {
    }

    override fun initView() {
        initNavigation()
        img_drawer_menu.setOnClickListener { drawer_main.openDrawer(Gravity.START) }
    }

    override fun loadData() {
        mPresenter!!.requestCalendar()
        vp_main.apply {
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
//        逻辑得改
//        if (fragmentList.size < User.userCalendarList.size) {
//            for (i in 0..User.userCalendarList.size - fragmentList.size) {
//                fragmentList.add(MainContentFragment())
//            }
//        } else if (fragmentList.size > User.userCalendarList.size) {
//            for (i in 0..fragmentList.size - User.userCalendarList.size) {
//                fragmentList.removeAt(i)
//            }
//        }
        adapter.dataList = calendarList
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
        //TODO(全部重改)
        nv_menu_left.let {
            it.inflateHeaderView(R.layout.view_navigation_head).let {
                it.findViewById<TextView>(R.id.txt_user_name).text = User.name
                it.findViewById<TextView>(R.id.txt_user_sign).text = if (TextUtils.isEmpty(User.signature)) "您还没有设置签名" else User.signature
                Glide.with(it.context)
                        .load(User.avatar)
                        .error(R.drawable.ic_default_avatar)
                        .bitmapTransform(CropCircleTransformation(it.context))
                        .into(it.img_drawer_user_avatar)
            }
            it.setNavigationItemSelectedListener { item ->
                when (item.itemId) {
                    R.id.menu_square -> startActivity(Intent(this@MainActivity, SquareActivity::class.java))
                    R.id.menu_create -> startActivity(Intent(this@MainActivity, ManageCalendarActivity::class.java))
                    R.id.menu_exit -> App.instance.toLogin()
                    R.id.menu_mine -> startActivity(Intent(this@MainActivity, MineActivity::class.java))
                }
                return@setNavigationItemSelectedListener true
            }
        }
    }

    override fun onRestart() {
        super.onRestart()
        vp_main.currentItem = 0
        mPresenter!!.requestCalendar()
    }

    override fun onDestroy() {
        super.onDestroy()
        mPresenter?.onDetach()
    }
}
