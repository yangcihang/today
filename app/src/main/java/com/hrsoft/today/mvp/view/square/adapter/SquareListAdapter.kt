package com.hrsoft.today.mvp.view.square.adapter

import android.content.Context
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide
import com.hrsoft.today.R
import com.hrsoft.today.base.BaseRecyclerAdapter
import com.hrsoft.today.mvp.model.SimpleCalendarModel

/**
 * @author YangCihang
 * @since  17/11/5.
 * email yangcihang@hrsoft.net
 */
class SquareListAdapter(mContext: Context) : BaseRecyclerAdapter<SimpleCalendarModel>(mContext) {
    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): BaseViewHolder<SimpleCalendarModel> {
        return ItemHolder(inflater.inflate(R.layout.item_square_content, parent, false))
    }

    inner class ItemHolder(itemView: View) : BaseRecyclerAdapter.BaseViewHolder<SimpleCalendarModel>(itemView) {
        private var titleTxt: TextView = itemView.findViewById(R.id.txt_square_calendar_title)
        private var calendarImg: ImageView = itemView.findViewById(R.id.img_square_calendar)
        override fun onBind(position: Int) {
            titleTxt.text = mData?.title
            Glide.with(mContext).load(mData?.picture).placeholder(R.mipmap.ic_launcher).into(calendarImg)
        }
    }
}