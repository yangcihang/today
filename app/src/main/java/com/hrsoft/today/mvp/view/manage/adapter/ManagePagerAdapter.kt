package com.hrsoft.today.mvp.view.manage.adapter

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter
import com.hrsoft.today.base.BaseFragment
import com.hrsoft.today.mvp.view.manage.fragment.SubscribedFragment
import com.hrsoft.today.mvp.view.manage.fragment.UserCreatedFragment

/**
 * @author YangCihang
 * @since  17/11/7.
 * email yangcihang@hrsoft.net
 */
class ManagePagerAdapter(fm: FragmentManager?) : FragmentPagerAdapter(fm) {
    private val createdPage = 0
    private val subscribedPage = 1
    private var fragmentList: MutableList<BaseFragment> = mutableListOf()
    private var userCreatedFragment: UserCreatedFragment = UserCreatedFragment()
    private var subscribedFragment: SubscribedFragment = SubscribedFragment()

    init {
        fragmentList.add(userCreatedFragment)
        fragmentList.add(subscribedFragment)
    }

    override fun getItem(position: Int): Fragment {
        return fragmentList[position]
    }

    override fun getCount(): Int {
        return fragmentList.size
    }

    override fun getPageTitle(position: Int): CharSequence? {
        var title: String? = null
        when (position) {
            createdPage -> title = "我创建的"
            subscribedPage -> title = "我订阅的"
        }
        return title
    }
}