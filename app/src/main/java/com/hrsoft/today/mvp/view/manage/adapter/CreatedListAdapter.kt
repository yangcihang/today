package com.hrsoft.today.mvp.view.manage.adapter

import android.content.Context
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide
import com.hrsoft.today.R
import com.hrsoft.today.base.BaseRecyclerAdapter
import com.hrsoft.today.mvp.model.models.SimpleCalendarModel

/**
 * @author YangCihang
 * @since  17/11/6.
 * email yangcihang@hrsoft.net
 */
class CreatedListAdapter(mContext: Context) : BaseRecyclerAdapter<SimpleCalendarModel>(mContext) {
    var onEditClickedListener: ((pos: Int, model: SimpleCalendarModel) -> Unit)? = null
    var onDeleteClickedListener: ((pos: Int, model: SimpleCalendarModel) -> Unit)? = null
    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): BaseViewHolder<SimpleCalendarModel> {
        return ItemHolder(inflater.inflate(R.layout.item_manage_calendar, parent, false))
    }

    inner class ItemHolder(itemView: View) : BaseRecyclerAdapter.BaseViewHolder<SimpleCalendarModel>(itemView) {
        private var iconImg: ImageView = itemView.findViewById(R.id.img_manage_calendar)
        private var titleTxt: TextView = itemView.findViewById(R.id.txt_manage_title)
        private var editTxt: TextView = itemView.findViewById(R.id.txt_manage_edit)
        private var deleteTxt: TextView = itemView.findViewById(R.id.txt_manage_delete)
        override fun onBind(position: Int) {
            Glide.with(mContext).load(mData?.picture).placeholder(R.mipmap.ic_launcher).into(iconImg)
            titleTxt.text = mData?.title
            editTxt.setOnClickListener { mData?.let { onEditClickedListener?.invoke(position, it) } }
            deleteTxt.setOnClickListener { mData?.let { onDeleteClickedListener?.invoke(position, it) } }
        }
    }
}