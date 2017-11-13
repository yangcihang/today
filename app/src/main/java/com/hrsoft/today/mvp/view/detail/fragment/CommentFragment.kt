package com.hrsoft.today.mvp.view.detail.fragment

import android.content.Context
import android.support.v7.widget.LinearLayoutManager
import com.hrsoft.today.R
import com.hrsoft.today.base.BaseFragment
import com.hrsoft.today.base.RecyclerScrollListener
import com.hrsoft.today.mvp.contract.CommentContract
import com.hrsoft.today.mvp.model.CommentModel
import com.hrsoft.today.mvp.presenter.CommentFragmentPresenter
import com.hrsoft.today.mvp.view.detail.adapter.CommentAdapter
import com.hrsoft.today.util.ToastUtil
import kotlinx.android.synthetic.main.fragment_calendar_comment.*

/**
 * @author abtion.
 * @since 17/11/6 21:08.
 * email caiheng@hrsoft.net.
 */
class CommentFragment : BaseFragment(), CommentContract.View {
    override var mPresenter: CommentContract.Presenter? = CommentFragmentPresenter(this)
    private var calendarId: Int? = null
    private var page = 1
    private var isLastPage = false
    private var commentAdapter: CommentAdapter? = null
    override fun getLayoutId(): Int {
        return R.layout.fragment_calendar_comment
    }

    override fun initVariable() {
        commentAdapter = CommentAdapter(activity)
    }

    override fun initView() {
        rec_calendar_comment.apply {
            adapter = commentAdapter
            layoutManager = LinearLayoutManager(this@CommentFragment.context)
            addOnScrollListener(RecyclerScrollListener({ if (!isLastPage) mPresenter?.getComment(page++, calendarId!!) }))
        }
    }

    override fun loadData() {
        mPresenter?.getComment(page++, calendarId!!)
    }

    interface GetCalendarIdListener {
        fun spreadCalendarId(): Int
    }

    override fun onAttach(context: Context?) {
        super.onAttach(context)
        val getCalendarIdLister: GetCalendarIdListener = context as GetCalendarIdListener
        calendarId = getCalendarIdLister.spreadCalendarId()
    }

    override fun onCommentListLoaded(dataList: List<CommentModel>) {
        commentAdapter?.refreshData(dataList)
    }

    override fun onToTheLastPage() {
        ToastUtil.showToast(R.string.toast_is_last_page)
        isLastPage = true
    }

    override fun onCommentListLoadFailed() {
        ToastUtil.showToast("评论获取失败，请稍后再试")
    }
}