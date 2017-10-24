package com.hrsoft.today.base

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View

/**
 * @author YangCihang
 * @since  17/9/26.
 * email yangcihang@hrsoft.net
 */
abstract class BaseRecyclerAdapter<Data>(var mContext: Context)
    : RecyclerView.Adapter<BaseRecyclerAdapter<Data>.BaseViewHolder<Data>>() {
    //dataList(kotlin中的List为只读，用Mutablelist)
    var dataList: MutableList<Data> = ArrayList()
    //inflater
    private var inflater: LayoutInflater = LayoutInflater.from(mContext)
    //点击事件的回调
    var onClickedListener: ((model: Data, pos: Int) -> Unit)? = null

    /**
     * 刷新数据源
     */
    fun refreshData(data: Collection<Data>) {
        this.dataList.clear()
        this.dataList.addAll(data)
        notifyDataSetChanged()
    }

    /**
     * 刷新数据源
     */
    fun refreshData() = notifyDataSetChanged()

    /**
     * 添加数据源
     */
    fun add(data: Data) {
        this.dataList.add(data)
        refreshData()
    }

    /**
     * 移除数据源
     */
    fun remove(data: Data) {
        this.dataList.remove(data)
        refreshData()
    }

    /**
     * 添加多条数据
     */
    fun addAll(data: Collection<Data>) {
        this.dataList.addAll(data)
        refreshData()
    }

    /**
     * 清空列表
     */
    fun clear() {
        this.dataList.clear()
        refreshData()
    }

    /**
     * getItemCount
     */
    override fun getItemCount(): Int {
        return this.dataList.size
    }

    /**
     * 绑定holder
     */
    override fun onBindViewHolder(holderBase: BaseViewHolder<Data>?, position: Int) {
        //ViewHolder绑定数据
        val data = dataList[position]
        holderBase?.itemView?.setOnClickListener {
            onClickedListener?.invoke(data, position)
        }
        holderBase?.bind(data, position)
    }

    inner abstract class BaseViewHolder<Data>(itemView: View?) : RecyclerView.ViewHolder(itemView) {
        protected var mData: Data? = null
        protected abstract fun onBind(position: Int)

        fun bind(data: Data, position: Int) {
            mData = data
            onBind(position)
        }
    }
}