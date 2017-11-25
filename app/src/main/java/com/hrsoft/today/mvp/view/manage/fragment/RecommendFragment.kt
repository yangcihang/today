package com.hrsoft.today.mvp.view.manage.fragment

import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.text.TextUtils
import com.hrsoft.today.R
import com.hrsoft.today.base.BaseFragment
import com.hrsoft.today.common.Config
import com.hrsoft.today.mvp.contract.CreateRecommendContract
import com.hrsoft.today.mvp.model.models.CalendarRecommendModel
import com.hrsoft.today.mvp.presenter.CreateRecommendFragmentPresenter
import com.hrsoft.today.mvp.view.manage.activity.CreateCalendarActivity
import com.hrsoft.today.mvp.view.manage.adapter.RecommendAddAdapter
import com.hrsoft.today.mvp.view.manage.adapter.RecommendContentAdapter
import com.hrsoft.today.util.ToastUtil
import kotlinx.android.synthetic.main.fragment_recommend.*

/**
 * @author YangCihang
 * @since  17/11/6.
 * email yangcihang@hrsoft.net
 */
class RecommendFragment : BaseFragment(), CreateRecommendContract.View {
    override var mPresenter: CreateRecommendContract.Presenter? = CreateRecommendFragmentPresenter(this)
    private lateinit var recommendAddAdapter: RecommendAddAdapter
    private lateinit var recommendContentAdapter: RecommendContentAdapter
    private val miniNum = 1
    private val maxNum = 20
    private var pickNum = 1
    private var calendarId = CreateCalendarActivity.DEFAULT_ID

    companion object {
        /**
         * 静态启动
         */
        fun createFragment(id: Int): RecommendFragment {
            val bundle = Bundle()
            bundle.putInt(Config.KEY_CALENDAR, id)
            return RecommendFragment().apply { arguments = bundle }
        }

    }

    override fun initVariable() {
        calendarId = arguments.getInt(Config.KEY_CALENDAR)
        recommendAddAdapter = RecommendAddAdapter(context)
        recommendContentAdapter = RecommendContentAdapter(context)
    }

    override fun getLayoutId(): Int = R.layout.fragment_recommend

    /**
     * 初始化View
     */
    override fun initView() {
        initRecycler()
        //初始化加减控件
        pick_recommend.let {
            it.miniNum = miniNum
            it.maxNum = maxNum
            it.pickNum = pickNum
        }
        //添加
        img_add_title.setOnClickListener {
            recommendAddAdapter.add("", recommendAddAdapter.dataList.size)
        }
        initSaveView()
    }

    override fun loadData() {
        if (calendarId != CreateCalendarActivity.DEFAULT_ID) {
            showProgressDialog(R.string.dialog_wait)
            mPresenter?.getRecommendDetail(calendarId)
        }
    }

    /**
     * 初始化保存按钮
     */
    private fun initSaveView() {
        //保存
        txt_state_save.setOnClickListener {
            when {
            //标题title判断
                (TextUtils.isEmpty(edit_recommend_name.text) || edit_recommend_name.text.length < 3 ||
                        edit_recommend_name.text.length > 20) -> {
                    ToastUtil.showToast(R.string.toast_recommend_title_name_error)
                    return@setOnClickListener
                }
            //数量判断
                pick_recommend.pickNum <= recommendAddAdapter.dataList.size -> {
                    recommendAddAdapter.dataList
                            .filter { TextUtils.isEmpty(it) }
                            .forEach {
                                ToastUtil.showToast(R.string.toast_recommend_content_empty)
                                return@setOnClickListener
                            }
                    val recommendContentList: MutableList<String> = mutableListOf()
                    recommendContentList.addAll(recommendAddAdapter.dataList)
                    recommendContentAdapter.add(CalendarRecommendModel(edit_recommend_name.text.toString().trim()
                            , recommendContentList, pickCount = pick_recommend.pickNum).apply { setItem() })
                }
                else -> ToastUtil.showToast(R.string.toast_recommend_content_num_error)
            }
        }
    }

    /**
     * 初始化recyclerView
     */
    private fun initRecycler() {
        rec_recommend_add.let {
            it.adapter = recommendAddAdapter
            it.layoutManager = LinearLayoutManager(context)
            it.isNestedScrollingEnabled = false
        }
        rec_recommend_content.let {
            it.adapter = recommendContentAdapter
            it.layoutManager = LinearLayoutManager(context)
            it.isNestedScrollingEnabled = false
        }
        recommendAddAdapter.add("")
        recommendAddAdapter.add("")
    }

    /**
     * 获取recommendData
     */
    fun updateRecommendData() {
        showProgressDialog(R.string.dialog_wait)
        mPresenter?.updateRecommendModel((context as CreateCalendarActivity).calendarId.toInt(), recommendContentAdapter.dataList)
        recommendContentAdapter.dataList
    }

    /**
     * 创建推荐项成功时回调
     */
    override fun onCreateRecommendSuccess() {
        disMissProgressDialog()
        ToastUtil.showToast("创建推荐页成功")
        (context as CreateCalendarActivity).changeFragmentCallback?.invoke()
    }

    /**
     * 创建推荐项失败时回调
     */
    override fun onCreateRecommendFailed() {
        disMissProgressDialog()
        ToastUtil.showToast("创建推荐页失败")
    }

    /**
     * 获取到推荐详情时回调
     */
    override fun onRecommendDetailLoadSuccess(recommendList: List<CalendarRecommendModel>) {
        disMissProgressDialog()
        recommendContentAdapter.addAll(recommendList)
    }

    override fun onRecommendDetailLoadFailed() {
        disMissProgressDialog()
        ToastUtil.showToast("加载失败")
    }

}