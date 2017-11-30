package com.hrsoft.today.mvp.view.manage.adapter

import android.content.Context
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.RelativeLayout
import android.widget.TextView
import com.hrsoft.today.R
import com.hrsoft.today.base.BaseRecyclerAdapter
import com.hrsoft.today.mvp.model.models.CalendarStateItemModel
import com.hrsoft.today.util.Utility

/**
 * @author YangCihang.
 * @since 17/11/15 17:08.
 * email yangcihang@hrsoft.net.
 */
class CreatedStateAdapter(mContext: Context) : BaseRecyclerAdapter<CalendarStateItemModel>(mContext) {
    private var flagList: MutableList<Boolean> = mutableListOf()
    override fun addAll(data: Collection<CalendarStateItemModel>) {
        super.addAll(data)
        for (i in 0 until dataList.size) flagList.add(false)
    }

    override fun add(data: CalendarStateItemModel) {
        super.add(data)
        flagList.add(false)
    }

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): BaseViewHolder<CalendarStateItemModel> {
        return ItemHolder(inflater.inflate(R.layout.item_create_calendar_state, parent, false))
    }

    inner class ItemHolder(itemView: View) : BaseRecyclerAdapter.BaseViewHolder<CalendarStateItemModel>(itemView) {
        private var expandRl: RelativeLayout = itemView.findViewById(R.id.rl_create_state_expand)
        private var expandLl: LinearLayout = itemView.findViewById(R.id.ll_state_item_layout)
        private var deleteImg: ImageView = itemView.findViewById(R.id.img_state_icon_delete)
        private var titleTxt: TextView = itemView.findViewById(R.id.txt_state_title)
        private var goodTxt: TextView = itemView.findViewById(R.id.txt_state_good)
        private var badTxt: TextView = itemView.findViewById(R.id.txt_state_bad)
        override fun onBind(position: Int) {
            if (flagList[position]) expandLl.visibility = View.VISIBLE else expandLl.visibility = View.GONE
            deleteImg.setOnClickListener {
                flagList.removeAt(position)
                remove(mData!!)
            }
            expandRl.setOnClickListener {
                Utility.runOnUiThread(Runnable {
                    flagList[position] = !flagList[position]
                    (0 until flagList.size)
                            .filter { it != position }
                            .forEach { flagList[it] = false }
                    refreshData()
                })
            }

            mData?.apply {
                titleTxt.text = name
                goodTxt.text = good
                badTxt.text = bad
            }
        }
    }
}