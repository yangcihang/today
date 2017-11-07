package com.hrsoft.today.base

import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView

/**
 * @author YangCihang
 * @since  17/11/5.
 * email yangcihang@hrsoft.net
 */

class RecyclerScrollListener(private var onScrolledToLast: ((Int) -> Unit)) : RecyclerView.OnScrollListener() {

    override fun onScrollStateChanged(recyclerView: RecyclerView?, newState: Int) {
        super.onScrollStateChanged(recyclerView, newState)
        // 滑动停止
        if (newState == RecyclerView.SCROLL_STATE_IDLE) {
            val layoutManager = recyclerView!!.layoutManager as LinearLayoutManager

            //获取最后一个完全显示的ItemPosition ,角标值
            val lastVisibleItem = layoutManager.findLastCompletelyVisibleItemPosition()
            val totalItemCount = layoutManager.itemCount
            // 判断是否滚动到底部
            if (lastVisibleItem == totalItemCount - 1) {
                onScrolledToLast.invoke(lastVisibleItem)
            }
        }
    }

}
