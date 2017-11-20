package com.hrsoft.today.mvp.view.manage.adapter

import android.content.Context
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide
import com.hrsoft.today.App
import com.hrsoft.today.R
import com.hrsoft.today.base.BaseRecyclerAdapter
import com.hrsoft.today.mvp.model.CalendarModel
import com.hrsoft.today.mvp.model.SimpleCalendarModel

/**
 * @author YangCihang.
 * @since 17/11/13 12:43.
 * email yangcihang@hrsoft.net.
 */
class SubscribedListAdapter(mContext: Context) : BaseRecyclerAdapter<CalendarModel>(mContext) {
    var onDeleteClickedListener: ((pos: Int, model: CalendarModel) -> Unit)? = null
    var onEditClickedListener: ((model: CalendarModel) -> Unit)? = null
    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): BaseViewHolder<CalendarModel> {
        return ItemHolder(inflater.inflate(R.layout.item_manage_calendar, parent, false))
    }

    inner class ItemHolder(itemView: View) : BaseRecyclerAdapter.BaseViewHolder<CalendarModel>(itemView) {
        private var iconImg: ImageView = itemView.findViewById(R.id.img_manage_calendar)
        private var titleTxt: TextView = itemView.findViewById(R.id.txt_manage_title)
        private var editTxt: TextView = itemView.findViewById(R.id.txt_manage_edit)
        private var deleteTxt: TextView = itemView.findViewById(R.id.txt_manage_delete)
        override fun onBind(position: Int) {
            editTxt.visibility = View.GONE
            deleteTxt.text = mContext.getString(R.string.text_cancel_subscribed)
            titleTxt.text = mData?.calendarName
            Glide.with(mContext).load(mData?.calendarPicture).placeholder(R.mipmap.ic_launcher).into(iconImg)
            deleteTxt.setOnClickListener { mData?.let { onDeleteClickedListener?.invoke(position, it) } }
            editTxt.setOnClickListener { onEditClickedListener?.invoke(mData!!) }
        }

    }
}