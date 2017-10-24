package com.hrsoft.today.mvp.view.main

import android.content.Context
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.hrsoft.today.base.BaseRecyclerAdapter

/**
 * @author YangCihang
 * @since  17/10/24.
 * email yangcihang@hrsoft.net
 */
class TestAdapter(mContext: Context) : BaseRecyclerAdapter<String>(mContext) {
    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): BaseViewHolder<String> {
        val textView: TextView = TextView(mContext)
        return ViewHolder(textView)
    }

    inner class ViewHolder(itemView: View?) : BaseViewHolder<String>(itemView) {
        override fun onBind(position: Int) {
            (itemView as TextView).text = mData
        }
    }
}