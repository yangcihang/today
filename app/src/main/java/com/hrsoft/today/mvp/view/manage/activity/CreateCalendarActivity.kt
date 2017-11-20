package com.hrsoft.today.mvp.view.manage.activity

import android.content.Context
import android.content.Intent
import com.hrsoft.today.R
import com.hrsoft.today.base.BaseFragment
import com.hrsoft.today.base.NoBarActivity
import com.hrsoft.today.common.Config
import com.hrsoft.today.mvp.contract.CreateContract
import com.hrsoft.today.mvp.model.CalendarModel
import com.hrsoft.today.mvp.presenter.CreateCalendarActivityPresenter
import com.hrsoft.today.mvp.view.detail.activity.CalendarDetailActivity
import com.hrsoft.today.mvp.view.manage.fragment.CalendarDescriptionFragment
import com.hrsoft.today.mvp.view.manage.fragment.RecommendFragment
import com.hrsoft.today.mvp.view.manage.fragment.StateFragment
import com.hrsoft.today.util.FragmentUtil
import com.hrsoft.today.util.ToastUtil
import kotlinx.android.synthetic.main.activity_create_calendar.*

/**
 * @author YangCihang
 * @since  17/11/6.
 * email yangcihang@hrsoft.net
 */
//TODO(此类和其fragment有时间会重写)
class CreateCalendarActivity : NoBarActivity(), CreateContract.View {

    override var mPresenter: CreateContract.Presenter? = CreateCalendarActivityPresenter(this)
    private lateinit var descriptionFragment: CalendarDescriptionFragment
    private lateinit var recommendFragment: RecommendFragment
    private lateinit var stateFragment: StateFragment
    private var changeFragmentListener: (() -> Unit)? = null
    //创建完后的id
    private var calendarId: Long = 0

    companion object {
        fun start(context: Context, model: CalendarModel) {
            context.startActivity(Intent(context, CreateCalendarActivity::class.java).apply {
                putExtra(Config.KEY_CALENDAR, model)
            })
        }
    }

    override fun initVariable() {
        descriptionFragment = CalendarDescriptionFragment()
        stateFragment = StateFragment()
        recommendFragment = RecommendFragment()
    }

    override fun initView() {
        replaceFragment(descriptionFragment)
        descriptionFragment.onRequestQiNiuTokenListener = {
            showProgressDialog(R.string.dialog_wait)
            mPresenter!!.upLoadPicture(it)
        }
        //TODO(用伴随方法？)
        txt_create_next.setOnClickListener {
            showProgressDialog(R.string.dialog_wait)
            when {
            //描述页面
                descriptionFragment.isResumed -> {
                    mPresenter?.createNewCalendar(descriptionFragment.getCalendarDescription())
                    changeFragmentListener = {
                        FragmentUtil.hideFragment(this, descriptionFragment)
                        replaceFragment(stateFragment)
                    }
                }
            //状态页面
                stateFragment.isResumed -> {
                    mPresenter?.createStateModel(calendarId, stateFragment.getStateData())
                    changeFragmentListener = {
                        FragmentUtil.hideFragment(this, stateFragment)
                        replaceFragment(recommendFragment)
                    }
                }
            //推荐页面
                recommendFragment.isResumed -> {
                    mPresenter?.createRecommendModel(calendarId.toInt(), recommendFragment.getRecommendData())
                    changeFragmentListener = { this@CreateCalendarActivity.finish() }
                }
            }
        }
    }

    override fun loadData() {
    }

    override fun getLayoutId(): Int {
        return R.layout.activity_create_calendar
    }

    /**
     * 创建新黄历成功时回调
     */
    override fun onCreateNewCalendarSuccess(id: Long) {
        disMissProgressDialog()
        calendarId = id
        changeFragmentListener?.invoke()
    }

    /**
     * 创建好坏成功时回调
     */
    override fun onCreateStateModelSuccess() {
        disMissProgressDialog()
        txt_create_next.text = "完成"
        changeFragmentListener?.invoke()
    }

    /**
     * 创建推荐成功时回调
     */
    override fun onCreateRecommendSuccess() {
        disMissProgressDialog()
        changeFragmentListener?.invoke()
    }

    /**
     * 创建新黄历失败时回调
     */
    override fun onCreateNewCalendarFailed() {
        disMissProgressDialog()
    }

    /**
     * 创建好坏失败时回调
     */
    override fun onCreateStateModelFailed() {
        disMissProgressDialog()
    }

    /**
     * 创建推荐失败时回调
     */

    override fun onCreateRecommendFailed() {
        disMissProgressDialog()
    }

    /**
     * 上传头像成功回调
     */
    override fun onPictureUploadSuccess(path: String) {
        disMissProgressDialog()
        descriptionFragment.netPicturePath = path
        ToastUtil.showToast("上传成功")

    }

    /**
     * 上传头像失败时候回调
     */
    override fun onPictureUploadFailed() {
        disMissProgressDialog()
        ToastUtil.showToast("上传失败")

    }

    private fun replaceFragment(fragment: BaseFragment) {
        FragmentUtil.replace(this, R.id.frame_create, fragment, null)
    }

    override fun onDestroy() {
        super.onDestroy()
        mPresenter?.onDetach()
    }

}