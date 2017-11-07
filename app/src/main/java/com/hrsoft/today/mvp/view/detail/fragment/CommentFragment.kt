package com.hrsoft.today.mvp.view.detail.fragment

import android.content.Context
import android.support.v7.widget.LinearLayoutManager
import com.hrsoft.today.R
import com.hrsoft.today.base.BaseFragment
import com.hrsoft.today.mvp.contract.CommentFragmentContrat
import com.hrsoft.today.mvp.presenter.CommentFragmentPresenter
import com.hrsoft.today.mvp.view.detail.adapter.CommentAdapter
import com.hrsoft.today.mvp.model.CommentModel
import com.hrsoft.today.util.ToastUtil
import kotlinx.android.synthetic.main.fragment_calendar_comment.*

/**
 * @author abtion.
 * @since 17/11/6 21:08.
 * email caiheng@hrsoft.net.
 */
class CommentFragment : BaseFragment(), CommentFragmentContrat.View {


    override var mPresenter: CommentFragmentContrat.Presenter? = CommentFragmentPresenter(this)
    var calendarId = 0
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
            layoutManager = LinearLayoutManager(this@CommentFragment.context, LinearLayoutManager.VERTICAL, false)
        }
    }

    override fun loadData() {
        mPresenter?.getComment(calendarId)
    }

    interface GetCalendarIdListener {
        fun spreadCalendarId(): Int
    }

    override fun onAttach(context: Context?) {
        super.onAttach(context)
        var getCalendarIdLister: GetCalendarIdListener = context as GetCalendarIdListener
        calendarId = getCalendarIdLister.spreadCalendarId()
    }

    override fun onCommentListLoaded(dataList: List<CommentModel>) {
        commentAdapter?.refreshData(dataList)
    }

    override fun onCommentListLoadFailed() {
        ToastUtil.showToast("评论获取失败，请稍后再试")
    }
}