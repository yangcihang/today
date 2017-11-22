package com.hrsoft.today.mvp.view.main.adapter

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter
import android.support.v4.app.FragmentTransaction
import android.support.v4.view.PagerAdapter
import android.view.ViewGroup
import com.hrsoft.today.mvp.model.CalendarModel
import com.hrsoft.today.mvp.view.main.fragment.MainContentFragment

/**
 * @author YangCihang
 * @since  17/10/31.
 * email yangcihang@hrsoft.net
 */
class MainPagerAdapter(private var fragmentManager: FragmentManager, var dataList: MutableList<CalendarModel>) :
        FragmentPagerAdapter(fragmentManager) {

    override fun getItem(position: Int): Fragment {
        return MainContentFragment.createFragment(dataList[position])
    }

    override fun getCount(): Int {
        return dataList.size
    }

    override fun getItemPosition(`object`: Any?): Int {
        return PagerAdapter.POSITION_NONE
    }

//    override fun instantiateItem(container: ViewGroup?, position: Int): Any {
//        container?.let { removeFragment(it, position) }
//        val fragment = super.instantiateItem(container, position) as MainContentFragment
//        fragment.calendar = dataList[position]
//        return fragment
//    }

    /**
     * 不作处理，否则manager里面只保存当前的fragment和左右两边的
     */
    override fun destroyItem(container: ViewGroup?, position: Int, `object`: Any?) {

    }

    override fun notifyDataSetChanged() {
        removeFragment()
        super.notifyDataSetChanged()
    }

    private fun removeFragment() {
        val mCurTransaction: FragmentTransaction = fragmentManager.beginTransaction()
        fragmentManager.fragments.forEach { mCurTransaction.remove(it) }
        mCurTransaction.commit()
        fragmentManager.executePendingTransactions()
    }
}
