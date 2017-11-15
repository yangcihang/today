package com.hrsoft.today.mvp.view.manage.activity

import android.widget.Toast
import com.hrsoft.today.R
import com.hrsoft.today.base.BaseFragment
import com.hrsoft.today.base.NoBarActivity
import com.hrsoft.today.mvp.contract.CreateContract
import com.hrsoft.today.mvp.presenter.CreateCalendarActivityPresenter
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
class CreateCalendarActivity : NoBarActivity(), CreateContract.View {

    override var mPresenter: CreateContract.Presenter? = CreateCalendarActivityPresenter(this)
    private lateinit var descriptionFragment: CalendarDescriptionFragment
    private lateinit var recommendFragment: RecommendFragment
    private lateinit var stateFragment: StateFragment
    private var changeFragmentListener: (() -> Unit)? = null
    private var calendarId: Long = 0
    override fun initVariable() {
        descriptionFragment = CalendarDescriptionFragment()
        recommendFragment = RecommendFragment()
        stateFragment = StateFragment()
    }

    override fun initView() {
        replaceFragment(descriptionFragment)
        descriptionFragment.onRequestQiNiuTokenListener = { mPresenter!!.upLoadPicture(it) }
        txt_create_next.setOnClickListener {
            showProgressDialog(R.string.toast_wait)
            when {
                descriptionFragment.isResumed -> {
                    mPresenter?.createNewCalendar(descriptionFragment.getCalendarDescription())
                    changeFragmentListener = {
                        FragmentUtil.hideFragment(this, descriptionFragment)
                        replaceFragment(stateFragment)
                    }
                }

                stateFragment.isResumed -> {
                    mPresenter?.createStateModel(calendarId, stateFragment.stateList)
                    changeFragmentListener = {
                        FragmentUtil.hideFragment(this, stateFragment)
                        replaceFragment(recommendFragment)
                    }
                }

                recommendFragment.isResumed -> {
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
    override fun onPictureUploadSuccess(path: String) {
        descriptionFragment.netPicturePath = path
        ToastUtil.showToast("上传成功")

    }

    override fun onPictureUploadFailed() {
        disMissProgressDialog()
        ToastUtil.showToast("上传失败")

    }

    override fun onCreateRecommendFailed() {
        disMissProgressDialog()
    }

    private fun replaceFragment(fragment: BaseFragment) {
        FragmentUtil.replace(this, R.id.frame_create, fragment, null)
    }

    override fun onDestroy() {
        super.onDestroy()
        mPresenter?.onDetach()
    }

}