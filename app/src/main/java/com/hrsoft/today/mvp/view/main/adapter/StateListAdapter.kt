package com.hrsoft.today.mvp.view.main.adapter

import android.content.Context
import android.os.Build
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.hrsoft.today.R
import com.hrsoft.today.base.BaseRecyclerAdapter
import com.hrsoft.today.mvp.model.models.CalendarStateModel

/**
 * @author YangCihang
 * @since  17/11/1.
 * email yangcihang@hrsoft.net
 */
class StateListAdapter(mContext: Context, var isGood: Boolean) : BaseRecyclerAdapter<CalendarStateModel>(mContext) {
    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): BaseViewHolder<CalendarStateModel> {
        return ItemHolder(inflater.inflate(R.layout.item_main_calender_state, parent, false))
    }

    inner class ItemHolder(itemView: View) : BaseViewHolder<CalendarStateModel>(itemView) {
        var titleTxt: TextView = itemView.findViewById(R.id.txt_calendar_state_title)
        var descriptionTxt: TextView = itemView.findViewById(R.id.txt_calendar_description)
        var flagIcon: ImageView = itemView.findViewById(R.id.img_flag_icon)
        override fun onBind(position: Int) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
                flagIcon.background =
                        if (isGood) mContext.resources.getDrawable(R.color.green)
                        else mContext.resources.getDrawable(R.color.red)
            }
            titleTxt.text = mData?.title
            descriptionTxt.text = mData?.description
        }
    }

}
