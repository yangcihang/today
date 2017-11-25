package com.hrsoft.today.mvp.view.manage.activity

import android.content.Context
import android.content.Intent
import com.hrsoft.today.R
import com.hrsoft.today.base.BaseFragment
import com.hrsoft.today.base.NoBarActivity
import com.hrsoft.today.common.Config
import com.hrsoft.today.mvp.view.manage.fragment.CalendarDescriptionFragment
import com.hrsoft.today.mvp.view.manage.fragment.RecommendFragment
import com.hrsoft.today.mvp.view.manage.fragment.StateFragment
import com.hrsoft.today.util.FragmentUtil
import kotlinx.android.synthetic.main.activity_create_calendar.*

/**
 * @author YangCihang
 * @since  17/11/6.
 * email yangcihang@hrsoft.net
 */
class CreateCalendarActivity : NoBarActivity() {
    private lateinit var descriptionFragment: CalendarDescriptionFragment
    private lateinit var recommendFragment: RecommendFragment
    private lateinit var stateFragment: StateFragment
    //启动类型，默认为-1
    private var editType = -1
    //更换fragment的回调
    var changeFragmentCallback: (() -> Unit)? = null
    //创建完后的id
    var calendarId: Long = -1
    //当黄历修改或者创建后的状态的sum
    var stateSum = 0
    //失败时取消dialog
    var onDialogDismissCallback = disMissProgressDialog()

    companion object {
        val DEFAULT_ID = -1
        val TYPE_CREATE = -1
        val TYPE_EDIT_DESCRIPTION = 0
        val TYPE_EDIT_STATE = 1
        val TYPE_EDIT_RECOMMEND = 2
        fun start(context: Context, id: Int, type: Int) {
            context.startActivity(Intent(context, CreateCalendarActivity::class.java).apply {
                putExtra(Config.KEY_CALENDAR, id)
                putExtra(Config.KEY_START_TYPE, type)
            })
        }
    }

    override fun initVariable() {
        calendarId = intent.extras.getInt(Config.KEY_CALENDAR, -1).toLong()
        editType = intent.extras.getInt(Config.KEY_START_TYPE, -1)
        if (calendarId.toInt() != -1) {
            txt_create_next.text = "完成"
            txt_create_title.text = "修改黄历"
        }
    }

    override fun initView() {
        when (editType) {
            TYPE_EDIT_DESCRIPTION -> onEditDescription()
            TYPE_EDIT_STATE -> onEditState()
            TYPE_EDIT_RECOMMEND -> onEditRecommend()
            else -> onCreateCalendar()
        }
    }

    override fun loadData() {
    }

    override fun getLayoutId(): Int {
        return R.layout.activity_create_calendar
    }

    /**
     * 替换fragment
     */
    private fun replaceFragment(fragment: BaseFragment) {
        FragmentUtil.replace(this, R.id.frame_create, fragment, null)
    }

    /**
     * 创建黄历的状态
     */
    private fun onCreateCalendar() {
        descriptionFragment = CalendarDescriptionFragment.createFragment(calendarId.toInt())
        stateFragment = StateFragment.createFragment(calendarId.toInt())
        recommendFragment = RecommendFragment.createFragment(calendarId.toInt())
        replaceFragment(descriptionFragment)
        txt_create_next.setOnClickListener {
            //            showProgressDialog(R.string.dialog_wait)
            when {
            //描述页面
                descriptionFragment.isResumed -> {
                    descriptionFragment.createCalendarDescription()
                    changeFragmentCallback = {
                        //                        disMissProgressDialog()
                        FragmentUtil.hideFragment(this, descriptionFragment)
                        replaceFragment(stateFragment)
                    }
                }
            //状态页面
                stateFragment.isResumed -> {
                    stateFragment.updateStateData()
                    changeFragmentCallback = {
                        //                        disMissProgressDialog()
                        txt_create_title.text = "完成"
                        FragmentUtil.hideFragment(this, stateFragment)
                        replaceFragment(recommendFragment)
                    }
                }
            //推荐页面
                recommendFragment.isResumed -> {
                    recommendFragment.updateRecommendData()
                    changeFragmentCallback = {
                        //                        disMissProgressDialog()
                        this@CreateCalendarActivity.finish()
                    }
                }
            }
        }
    }


    //TODO(以下统一整合)
    /**
     * 修改黄历信息的状态
     */
    private fun onEditDescription() {
        descriptionFragment = CalendarDescriptionFragment.createFragment(calendarId.toInt())
        replaceFragment(descriptionFragment)
        txt_create_next.setOnClickListener {
            descriptionFragment.updateCalendarDescription()
            changeFragmentCallback = {
                this.finish()
            }
        }
    }

    /**
     * 修改黄历状态的状态
     */
    private fun onEditState() {
        stateFragment = StateFragment.createFragment(calendarId.toInt())
        replaceFragment(stateFragment)
        txt_create_next.setOnClickListener {
            stateFragment.updateStateData()
            changeFragmentCallback = {
                this.finish()
            }
        }
    }

    /**
     * 修改黄历推荐的状态
     */
    private fun onEditRecommend() {
        recommendFragment = RecommendFragment.createFragment(calendarId.toInt())
        replaceFragment(recommendFragment)
        txt_create_next.setOnClickListener {
            recommendFragment.updateRecommendData()
            changeFragmentCallback = {
                this.finish()
            }
        }
    }

}