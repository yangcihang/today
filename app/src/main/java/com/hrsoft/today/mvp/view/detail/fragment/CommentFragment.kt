package com.hrsoft.today.mvp.view.detail.fragment

import android.content.Context
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.widget.Toast
import com.hrsoft.today.R
import com.hrsoft.today.base.BaseFragment
import com.hrsoft.today.base.RecyclerScrollListener
import com.hrsoft.today.common.Config
import com.hrsoft.today.mvp.contract.CommentContract
import com.hrsoft.today.mvp.model.CalendarDetailModel
import com.hrsoft.today.mvp.model.CommentModel
import com.hrsoft.today.mvp.model.User
import com.hrsoft.today.mvp.presenter.CommentFragmentPresenter
import com.hrsoft.today.mvp.view.detail.adapter.CommentAdapter
import com.hrsoft.today.util.TimeUtil
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
    private var sendCommentContent: String? = null
    private var commentAdapter: CommentAdapter? = null

    companion object {
        /**
         * 静态启动
         */
        fun createFragment(calendar: CalendarDetailModel?): CommentFragment {
            val bundle = Bundle()
            bundle.putParcelable(Config.KEY_CALENDAR_DETAIL, calendar)
            return CommentFragment().apply { arguments = bundle }
        }
    }

    override fun getLayoutId(): Int {
        return R.layout.fragment_calendar_comment
    }

    override fun initVariable() {
        calendarId = arguments.getParcelable<CalendarDetailModel>(Config.KEY_CALENDAR_DETAIL).id
        commentAdapter = CommentAdapter(activity)
    }

    override fun initView() {
        rec_calendar_comment.apply {
            adapter = commentAdapter
            layoutManager = LinearLayoutManager(this@CommentFragment.context)
            addOnScrollListener(RecyclerScrollListener({ if (!isLastPage) mPresenter?.getComment(page++, calendarId!!) }))
        }
        input_panel.onSendListener = { s ->
            run {
                mPresenter?.sendComment(calendarId!!, s)
                sendCommentContent = s
            }
        }
    }

    override fun loadData() {
        mPresenter?.getComment(page++, calendarId!!)
    }

    override fun onToTheLastPage() {
        ToastUtil.showToast(R.string.toast_is_last_page)
        isLastPage = true
    }

    override fun onCommentListLoaded(dataList: List<CommentModel>) {
        commentAdapter?.addAll(dataList)
    }


    override fun onCommentListLoadFailed() {
        ToastUtil.showToast("评论获取失败，请稍后再试")
    }


    override fun onSendCommentSuccess() {
        commentAdapter?.apply {
            dataList.add(0, CommentModel().apply {
                userName = User.name!!
                createdAt = System.currentTimeMillis()
                comment = sendCommentContent!!
                userAvatar = User.avatar!!
            })
            refreshData()
        }
        ToastUtil.showToast("评论成功")
    }

    override fun onSendCommentFailed() {
    }

}