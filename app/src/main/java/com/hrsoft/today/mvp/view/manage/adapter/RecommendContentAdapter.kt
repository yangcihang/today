package com.hrsoft.today.mvp.view.manage.adapter

import android.content.Context
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.hrsoft.today.R
import com.hrsoft.today.base.BaseRecyclerAdapter
import com.hrsoft.today.mvp.model.CalendarRecommendModel

/**
 * @author YangCihang.
 * @since 17/11/18 17:20.
 * email yangcihang@hrsoft.net.
 */
class RecommendContentAdapter(mContext: Context) : BaseRecyclerAdapter<CalendarRecommendModel>(mContext) {
    //标记是否展开
    private var flagList: MutableList<Boolean> = mutableListOf()

    override fun addAll(data: Collection<CalendarRecommendModel>) {
        super.addAll(data)
        for (i in 0 until dataList.size) flagList.add(false)
    }

    override fun add(data: CalendarRecommendModel) {
        super.add(data)
        flagList.add(false)
    }

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): BaseViewHolder<CalendarRecommendModel> {
        return ItemHolder(inflater.inflate(R.layout.item_recommend_content, parent, false))
    }

    inner class ItemHolder(itemView: View) : BaseRecyclerAdapter.BaseViewHolder<CalendarRecommendModel>(itemView) {
        private var titleTxt: TextView = itemView.findViewById(R.id.txt_recommend_content_title)
        private var contentRec: RecyclerView = itemView.findViewById(R.id.rec_content_item)
        override fun onBind(position: Int) {
            if (flagList[position]) contentRec.visibility = View.VISIBLE else contentRec.visibility = View.GONE
            //itemView点击展开listView
            itemView.setOnClickListener {
                flagList[position] = !flagList[position]
                (0 until flagList.size)
                        .filter { it != position }
                        .forEach { flagList[it] = false }
                refreshData()
            }
            titleTxt.text = mData?.name
            contentRec.let {
                it.adapter = ContentListAdapter(mContext).apply { addAll(mData?.items!!) }
                it.layoutManager = LinearLayoutManager(mContext)
            }
        }
    }

    /**
     * 每一个内容里面的RecyclerView的adapter
     */
    inner class ContentListAdapter(mContext: Context) : BaseRecyclerAdapter<String>(mContext) {
        override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): BaseViewHolder<String> {
            return ContentItemHolder(inflater.inflate(R.layout.item_recommend_content_item, parent, false))
        }

        inner class ContentItemHolder(itemView: View) : BaseRecyclerAdapter.BaseViewHolder<String>(itemView) {
            private var contentTxt: TextView = itemView.findViewById(R.id.txt_recommend_content)
            override fun onBind(position: Int) {
                Log.d("内部", "$position")
                contentTxt.text = dataList[position]
            }
        }
    }
}