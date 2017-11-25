package com.hrsoft.today.mvp.view.manage.fragment

import android.content.Intent
import android.support.v7.widget.LinearLayoutManager
import com.hrsoft.today.R
import com.hrsoft.today.base.BaseFragment
import com.hrsoft.today.mvp.contract.ManageCreatedContract
import com.hrsoft.today.mvp.model.models.SimpleCalendarModel
import com.hrsoft.today.mvp.presenter.ManageCreatedFragmentPresenter
import com.hrsoft.today.mvp.view.detail.activity.CalendarDetailActivity
import com.hrsoft.today.mvp.view.manage.activity.CreateCalendarActivity
import com.hrsoft.today.mvp.view.manage.adapter.CreatedListAdapter
import com.hrsoft.today.util.DialogUtils
import com.hrsoft.today.util.ToastUtil
import kotlinx.android.synthetic.main.fragment_created.*
import kotlinx.android.synthetic.main.view_dialog_edit_calendar.view.*

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
        initRecyclerView()
        fab_manage_create.setOnClickListener {
            CreateCalendarActivity.start(context, CreateCalendarActivity.DEFAULT_ID,
                    CreateCalendarActivity
                            .TYPE_CREATE)
        }
    }

    private fun initRecyclerView() {
        rec_created_calendar.let {
            it.layoutManager = LinearLayoutManager(context)
            it.adapter = this@UserCreatedFragment.listAdapter.apply {
                onClickedListener = { model, _ ->
                    CalendarDetailActivity.start(context, model)
                }
                onEditClickedListener = { _, model ->
                    run {
                        DialogUtils(context)
                                .setCustomView(R.layout.view_dialog_edit_calendar, { view ->
                                    run {
                                        view.txt_dialog_edit_description.setOnClickListener {
                                            CreateCalendarActivity
                                                    .start(context, model.id!!, CreateCalendarActivity
                                                            .TYPE_EDIT_DESCRIPTION)
                                        }
                                        view.txt_dialog_edit_state.setOnClickListener {
                                            CreateCalendarActivity
                                                    .start(context, model.id!!, CreateCalendarActivity
                                                            .TYPE_EDIT_STATE)
                                        }
                                        view.txt_dialog_edit_recommend.setOnClickListener {
                                            CreateCalendarActivity
                                                    .start(context, model.id!!, CreateCalendarActivity
                                                            .TYPE_EDIT_RECOMMEND)
                                        }
                                    }
                                })
                                .setCancelable(true)
                                .showAlertDialog()
                    }
                }

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