package com.hrsoft.today.mvp.view.manage.fragment

import android.graphics.Color
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.support.v7.widget.helper.ItemTouchHelper
import com.hrsoft.today.R
import com.hrsoft.today.base.BaseFragment
import com.hrsoft.today.mvp.contract.ManageSubscribedContract
import com.hrsoft.today.mvp.presenter.ManageSubscribedPresenter
import com.hrsoft.today.mvp.view.manage.adapter.SubscribedListAdapter
import kotlinx.android.synthetic.main.fragment_subscribed.*
import android.support.v7.widget.GridLayoutManager
import com.hrsoft.today.mvp.model.User
import com.hrsoft.today.util.ToastUtil
import java.util.*


/**
 * @author YangCihang
 * @since  17/11/7.
 * email yangcihang@hrsoft.net
 */
class SubscribedFragment : BaseFragment(), ManageSubscribedContract.View {
    override var mPresenter: ManageSubscribedContract.Presenter? = ManageSubscribedPresenter(this)
    private var deletePos: Int? = null
    private lateinit var adapter: SubscribedListAdapter
    private lateinit var itemTouchHelper: ItemTouchHelper
    override fun initVariable() {
        adapter = SubscribedListAdapter(context)
        User.userCalendarList.let { adapter.addAll(it) }
        itemTouchHelper = ItemTouchHelper(TouchHelper())
    }

    override fun getLayoutId(): Int {
        return R.layout.fragment_subscribed
    }


    override fun initView() {
        itemTouchHelper.attachToRecyclerView(rec_subscribed_calendar.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = this@SubscribedFragment.adapter.apply {
                onClickedListener = { model, pos ->
                    ToastUtil.showToast("" + pos)
                }
            }
        })
    }

    override fun loadData() {
    }

    /**
     * RecyclerView实现拖动的辅助类
     */
    inner class TouchHelper : ItemTouchHelper.Callback() {

        override fun getMovementFlags(recyclerView: RecyclerView?, viewHolder: RecyclerView.ViewHolder?): Int {
            return if (recyclerView?.layoutManager is GridLayoutManager) {
                val dragFlags = ItemTouchHelper.UP or ItemTouchHelper.DOWN or
                        ItemTouchHelper.LEFT or ItemTouchHelper.RIGHT
                val swipeFlags = 0
                ItemTouchHelper.Callback.makeMovementFlags(dragFlags, swipeFlags)
            } else {
                val dragFlags = ItemTouchHelper.UP or ItemTouchHelper.DOWN
                val swipeFlags = 0
                ItemTouchHelper.Callback.makeMovementFlags(dragFlags, swipeFlags)
            }
        }

        /**
         * 移动时回调
         */
        override fun onMove(recyclerView: RecyclerView?, viewHolder: RecyclerView.ViewHolder?, target: RecyclerView.ViewHolder?): Boolean {

            val fromPosition = viewHolder?.adapterPosition
            //拿到当前拖拽到的item的viewHolder
            val toPosition = target?.adapterPosition
            if (fromPosition!! < toPosition!!) {
                for (i in fromPosition until toPosition) {
                    Collections.swap(User.userCalendarList, i, i + 1)
                }
            } else {
                //降序
                for (i in fromPosition downTo toPosition + 1) {
                    Collections.swap(User.userCalendarList, i, i - 1)
                }
            }
            adapter.notifyItemMoved(fromPosition, toPosition)
            return true
        }

        /**
         * 不作处理
         */
        override fun onSwiped(viewHolder: RecyclerView.ViewHolder?, direction: Int) {
        }

        override fun onSelectedChanged(viewHolder: RecyclerView.ViewHolder?, actionState: Int) {
            if (actionState != ItemTouchHelper.ACTION_STATE_IDLE) {
                viewHolder?.itemView?.setBackgroundColor(Color.LTGRAY)
            }
            super.onSelectedChanged(viewHolder, actionState)
        }

        override fun clearView(recyclerView: RecyclerView?, viewHolder: RecyclerView.ViewHolder?) {
            super.clearView(recyclerView, viewHolder)
            viewHolder!!.itemView.setBackgroundColor(0)
        }
    }
}