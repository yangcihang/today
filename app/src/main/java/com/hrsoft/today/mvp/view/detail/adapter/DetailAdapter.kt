package com.hrsoft.today.mvp.view.detail.adapter

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter
import android.view.View
import com.hrsoft.today.base.BaseFragment
import com.hrsoft.today.mvp.view.detail.fragment.CommentFragment
import com.hrsoft.today.mvp.view.detail.fragment.DescriptionFragment

/**
 * @author abtion.
 * @since 17/11/6 21:14.
 * email caiheng@hrsoft.net.
 */
class DetailAdapter(fm: FragmentManager): FragmentPagerAdapter(fm) {



    private var titles= arrayListOf("描述","评论")
    private var currentFragment:BaseFragment=DescriptionFragment()
    private var descriptionFragment:DescriptionFragment = DescriptionFragment()
    private var commentFragment:CommentFragment = CommentFragment()

    override fun getItem(position: Int): Fragment {
        when(position){
            0-> currentFragment= descriptionFragment
            1-> {
                currentFragment= commentFragment
                TODO("隐藏FAB")
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