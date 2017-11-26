package com.hrsoft.today.mvp.view.detail.adapter

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter
import com.hrsoft.today.base.BaseFragment
import com.hrsoft.today.mvp.model.models.CalendarDetailModel
import com.hrsoft.today.mvp.view.detail.fragment.CommentFragment
import com.hrsoft.today.mvp.view.detail.fragment.PreviewFragment

/**
 * @author abtion.
 * @since 17/11/6 21:14.
 * email caiheng@hrsoft.net.
 */
class DetailPagerAdapter(fm: FragmentManager, calendarModel: CalendarDetailModel) : FragmentPagerAdapter(fm) {


    private var titles = arrayListOf("预览", "评论")
    private var currentFragment: BaseFragment = PreviewFragment()
    private var previewFragment: PreviewFragment = PreviewFragment.createFragment(calendarModel)
    private var commentFragment: CommentFragment = CommentFragment.createFragment(calendarModel)

    override fun getItem(position: Int): Fragment {
        when (position) {
            0 -> currentFragment = previewFragment
            1 -> {
                currentFragment = commentFragment

            }
        }
        return currentFragment
    }

    override fun getCount(): Int {
        return 2
    }

    override fun getPageTitle(position: Int): CharSequence {
        return titles[position]
    }
}