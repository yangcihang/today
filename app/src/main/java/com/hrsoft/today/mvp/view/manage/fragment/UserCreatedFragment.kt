package com.hrsoft.today.mvp.view.manage.fragment

import android.support.v7.widget.LinearLayoutManager
import com.hrsoft.today.R
import com.hrsoft.today.base.BaseFragment
import com.hrsoft.today.mvp.contract.ManageCreatedContract
import com.hrsoft.today.mvp.model.SimpleCalendarModel
import com.hrsoft.today.mvp.presenter.ManageCreatedFragmentPresenter
import com.hrsoft.today.mvp.view.detail.activity.CalendarDetailActivity
import com.hrsoft.today.mvp.view.manage.adapter.CreatedListAdapter
import com.hrsoft.today.util.DialogUtils
import com.hrsoft.today.util.ToastUtil
import kotlinx.android.synthetic.main.fragment_created.*

/**
 * @author YangCihang
 * @since  17/11/7.
 * email yangcihang@hrsoft.net
 */
class UserCreatedFragment : BaseFragment(), ManageCreatedContract.View {
    private lateinit var listAdapter: CreatedListAdapter
    override var mPresenter: ManageCreatedContract.Presenter? = ManageCreatedFragmentPresenter(this)
    private var deletePos: Int? = null
    override fun getLayoutId(): Int {
        return R.layout.fragment_created
    }

    override fun initVariable() {
        listAdapter = CreatedListAdapter(context)
    }

    override fun initView() {
        rec_created_calendar.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = this@UserCreatedFragment.listAdapter.apply {
                onClickedListener = { model, _ ->
                    CalendarDetailActivity.start(context, model)
                }
                onEditClickedListener = { pos, _ -> ToastUtil.showToast("点击了修改" + pos) }
                onDeleteClickedListener = { pos, model ->
                    deletePos = pos
                    DialogUtils(context)
                            .setPositiveButton {
                                mPresenter?.deleteCreatedCalendar(model.id!!)
                                showProgressDialog("加载中")
                            }
                            .setTitleText("您确认要删除吗？")
                            .setCancelable(false)
                            .setNegativeButton(null)
                            .showAlertDialog()
                }
            }
        }


    }

    override fun loadData() {
        mPresenter?.requestCreatedCalendar()
    }

    override fun onCalendarLoadSuccess(dataList: List<SimpleCalendarModel>) {
        listAdapter.addAll(dataList)
    }

    override fun onDeleteCalendarFailed() {
        disMissProgressDialog()
        ToastUtil.showToast("删除失败")
    }

    override fun onDeleteCalendarSuccess() {
        disMissProgressDialog()
        ToastUtil.showToast("删除成功")
        listAdapter.remove(deletePos!!)
    }

    override fun onDestroy() {
        super.onDestroy()
        mPresenter?.onDetach()
    }
}