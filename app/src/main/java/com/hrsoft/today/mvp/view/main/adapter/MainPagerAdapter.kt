package com.hrsoft.today.mvp.view.main.adapter

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter
import com.hrsoft.today.mvp.view.main.fragment.MainContentFragment

/**
 * @author YangCihang
 * @since  17/10/31.
 * email yangcihang@hrsoft.net
 */
class MainPagerAdapter(fragmentManager: FragmentManager, private var fragmentList: MutableList<MainContentFragment>) :
        FragmentPagerAdapter(fragmentManager) {

    override fun getItem(position: Int): Fragment {
        return fragmentList[position]
    }

    override fun getCount(): Int {
        return fragmentList.size
    }
}