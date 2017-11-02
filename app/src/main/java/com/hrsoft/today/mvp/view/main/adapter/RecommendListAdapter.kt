package com.hrsoft.today.mvp.view.main.adapter

import android.content.Context
import android.view.View
import android.view.ViewGroup
import com.hrsoft.today.R
import com.hrsoft.today.base.BaseRecyclerAdapter
import com.hrsoft.today.mvp.model.CalendarRecommendModel

/**
 * @author YangCihang
 * @since  17/11/2.
 * email yangcihang@hrsoft.net
 */
class RecommendListAdapter(mContext: Context) : BaseRecyclerAdapter<CalendarRecommendModel>(mContext) {
    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): BaseViewHolder<CalendarRecommendModel> {
        return ItemHolder(inflater.inflate(R.layout.item_main_calendar_recommend,parent,false))
    }

    class ItemHolder(itemView: View) : BaseViewHolder<CalendarRecommendModel>(itemView) {
        override fun onBind(position: Int) {
        }

    }
}