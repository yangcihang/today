package com.hrsoft.today.mvp.view.main.adapter

import android.content.Context
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.hrsoft.today.R
import com.hrsoft.today.base.BaseRecyclerAdapter
import com.hrsoft.today.mvp.model.CalendarStateModel

/**
 * @author YangCihang
 * @since  17/11/1.
 * email yangcihang@hrsoft.net
 */
class StateListAdapter(mContext: Context) : BaseRecyclerAdapter<CalendarStateModel>(mContext) {
    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): BaseViewHolder<CalendarStateModel> {
        return ItemHolder(inflater.inflate(R.layout.item_main_calender_state, parent, false))
    }

    class ItemHolder(itemView: View) : BaseViewHolder<CalendarStateModel>(itemView) {
        var titleTxt: TextView = itemView.findViewById(R.id.txt_calendar_title)
        var descriptionTxt: TextView = itemView.findViewById(R.id.txt_calendar_description)
        override fun onBind(position: Int) {
            titleTxt.text = "123123"
        }
    }

}
