package com.hrsoft.today.mvp.view.manage.fragment

import android.support.v7.widget.LinearLayoutManager
import android.text.TextUtils
import com.hrsoft.today.R
import com.hrsoft.today.base.BaseFragment
import com.hrsoft.today.mvp.model.CalendarRecommendModel
import com.hrsoft.today.mvp.view.manage.adapter.RecommendAddAdapter
import com.hrsoft.today.mvp.view.manage.adapter.RecommendContentAdapter
import com.hrsoft.today.util.ToastUtil
import kotlinx.android.synthetic.main.fragment_recommend.*

/**
 * @author YangCihang
 * @since  17/11/6.
 * email yangcihang@hrsoft.net
 */
class RecommendFragment : BaseFragment() {
    private lateinit var recommendAddAdapter: RecommendAddAdapter
    private lateinit var recommendContentAdapter: RecommendContentAdapter
    private val miniNum = 2
    private val maxNum = 20
    private var pickNum = 2
    override fun getLayoutId(): Int {
        return R.layout.fragment_recommend
    }

    override fun initVariable() {
        recommendAddAdapter = RecommendAddAdapter(context)
        recommendContentAdapter = RecommendContentAdapter(context)
    }

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
    fun getRecommendData(): List<CalendarRecommendModel> = recommendContentAdapter.dataList

    override fun loadData() {
    }
}