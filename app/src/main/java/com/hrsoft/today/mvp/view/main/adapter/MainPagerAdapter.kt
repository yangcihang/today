package com.hrsoft.today.mvp.view.main.adapter

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter
import android.support.v4.app.FragmentTransaction
import android.support.v4.view.PagerAdapter
import android.view.ViewGroup
import com.hrsoft.today.base.BaseFragment
import com.hrsoft.today.mvp.model.models.CalendarModel
import com.hrsoft.today.mvp.view.main.fragment.MainContentFragment

/**
 * @author YangCihang
 * @since  17/10/31.
 * email yangcihang@hrsoft.net
 */
class MainPagerAdapter(private var fragmentManager: FragmentManager, var dataList: MutableList<CalendarModel>) :
        FragmentPagerAdapter(fragmentManager) {
    //TODO 十分傻逼的方法，因为如果获取fragmentManager的所有fragment清除的话，会导致侧栏的Image无法渲染，因此用这个list保存所有的Pager里面的fragment
    private var fragmentList: MutableList<BaseFragment> = mutableListOf()

    override fun getItem(position: Int): Fragment {
        val fragment = MainContentFragment.createFragment(dataList[position])
        fragmentList.add(fragment)
        return fragment
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

    //每次notify的时候执行
    private fun removeFragment() {
        val mCurTransaction: FragmentTransaction = fragmentManager.beginTransaction()
        fragmentList.forEach { mCurTransaction.remove(it) }
        fragmentList.clear()
        //这样commit就不会在activity销毁时报错
        mCurTransaction.commitAllowingStateLoss()
        fragmentManager.executePendingTransactions()
    }
}
