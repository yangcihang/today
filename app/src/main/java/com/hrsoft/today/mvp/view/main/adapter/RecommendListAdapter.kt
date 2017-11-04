package com.hrsoft.today.mvp.view.main.adapter

import android.content.Context
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
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
        return ItemHolder(inflater.inflate(R.layout.item_main_calendar_recommend, parent, false))
    }

    inner class ItemHolder(itemView: View) : BaseViewHolder<CalendarRecommendModel>(itemView) {
        var flagIcon: ImageView = itemView.findViewById(R.id.img_recommend_flag_icon)
        var titleTxt: TextView = itemView.findViewById(R.id.txt_calendar_recommend_title)
        var descriptionTxt: TextView = itemView.findViewById(R.id.txt_calendar_recommend_description)
        override fun onBind(position: Int) {
            flagIcon.setBackgroundColor(mContext.resources.getColor(R.color.text_main_recommend_title))
            titleTxt.text = mData?.name
            var description = ""
            mData?.items?.forEach { description += it + " " }
            descriptionTxt.text = description
        }
    }
}