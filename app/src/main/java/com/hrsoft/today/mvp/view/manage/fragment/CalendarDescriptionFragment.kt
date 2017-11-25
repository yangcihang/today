package com.hrsoft.today.mvp.view.manage.fragment

import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextUtils
import com.bumptech.glide.Glide
import com.hrsoft.today.App
import com.hrsoft.today.R
import com.hrsoft.today.base.BaseFragment
import com.hrsoft.today.common.Config
import com.hrsoft.today.mvp.contract.CreateDescriptionContract
import com.hrsoft.today.mvp.model.models.CalendarDetailModel
import com.hrsoft.today.mvp.model.models.NewCalendarModel
import com.hrsoft.today.mvp.presenter.CreateDescriptionFragmentPresenter
import com.hrsoft.today.mvp.view.manage.activity.CreateCalendarActivity
import com.hrsoft.today.util.ImagePickerUtil
import com.hrsoft.today.util.ToastUtil
import com.lwkandroid.imagepicker.ImagePicker
import com.lwkandroid.imagepicker.data.ImageBean
import com.lwkandroid.imagepicker.data.ImagePickType
import kotlinx.android.synthetic.main.fragment_description.*


/**
 * @author YangCihang
 * @since  17/11/6.
 * email yangcihang@hrsoft.net
 */
class CalendarDescriptionFragment : BaseFragment(), CreateDescriptionContract.View {
    override var mPresenter: CreateDescriptionContract.Presenter? = CreateDescriptionFragmentPresenter(this)
    var netPicturePath = ""
    /** 好坏的范围：2~5个*/
    private var miniNum = 2
    private var maxNum = 5
    private var picturePath = ""
    private var calendarId = CreateCalendarActivity.DEFAULT_ID
    private var REQUEST_CODE = 1 shl 5

    companion object {
        /**
         * 静态启动
         */
        fun createFragment(id: Int): CalendarDescriptionFragment {
            val bundle = Bundle()
            bundle.putInt(Config.KEY_CALENDAR, id)
            return CalendarDescriptionFragment().apply { arguments = bundle }
        }

    }

    override fun getLayoutId(): Int = R.layout.fragment_description


    override fun initVariable() {
        calendarId = arguments.getInt(Config.KEY_CALENDAR)
    }

    override fun initView() {
        pick_good.let {
            it.pickNum = 2
            it.maxNum = maxNum
            it.miniNum = miniNum
        }
        pick_bad.let {
            it.pickNum = 2
            it.maxNum = maxNum
            it.miniNum = miniNum
        }
        img_create_calendar.setOnClickListener {
            ImagePicker()
                    .pickType(ImagePickType.SINGLE)
                    .needCamera(true)
                    .cachePath(App.instance.cachePath)
                    .displayer(ImagePickerUtil())
                    .doCrop(1, 1, 480, 480)
                    .start(this@CalendarDescriptionFragment, REQUEST_CODE)
        }
    }

    override fun loadData() {
        if (calendarId != CreateCalendarActivity.DEFAULT_ID) {
            showProgressDialog(R.string.dialog_wait)
            mPresenter?.loadDetail(calendarId.toLong())
        }
    }


    fun createCalendarDescription() {
        showProgressDialog(R.string.dialog_wait)
        mPresenter?.createNewCalendar(NewCalendarModel(title = edit_create_calendar_title.text.toString().trim(),
                description = edit_create_calendar_description.text.toString().trim(),
                badPick = pick_bad.pickNum,
                goodPick = pick_good.pickNum,
                picture = netPicturePath)
        )
    }

    /**
     * 获取日历的描述信息
     */
    fun updateCalendarDescription() {
        showProgressDialog(R.string.dialog_wait)
        mPresenter?.updateCalendar(calendarId, NewCalendarModel(title = edit_create_calendar_title.text.toString().trim(),
                description = edit_create_calendar_description.text.toString().trim(),
                badPick = pick_bad.pickNum,
                goodPick = pick_good.pickNum,
                picture = netPicturePath))
    }

    /**
     * 创建新黄历成功时回调
     */
    override fun onCreateNewCalendarSuccess(id: Long, stateNum: Int) {
        disMissProgressDialog()
        (context as CreateCalendarActivity).let {
            it.calendarId = id
            it.stateSum = stateNum
            it.changeFragmentCallback?.invoke()
        }
    }

    /**
     * 创建新黄历失败时回调
     */
    override fun onCreateNewCalendarFailed() {
        disMissProgressDialog()
        ToastUtil.showToast("黄历创建失败")
    }

    /**
     * 图片上传成功时回调
     */
    override fun onPictureUploadSuccess(netpath: String) {
        disMissProgressDialog()
        this.netPicturePath = netpath
        ToastUtil.showToast("上传成功")
    }

    /**
     * 图片上传失败时回调
     */
    override fun onPictureUploadFailed() {
        disMissProgressDialog()
        ToastUtil.showToast("上传失败")
    }

    /**
     * 获取黄历详情成功时候回调
     */
    override fun onDetailLoaded(mData: CalendarDetailModel) {
        disMissProgressDialog()
        Glide.with(context as CreateCalendarActivity).load(mData.picture).error(R.mipmap.ic_launcher).into(img_create_calendar)
        netPicturePath = mData.picture
        pick_good.setCurrentNum(mData.goodPick)
        pick_bad.setCurrentNum(mData.badPick)
        edit_create_calendar_title.setText(mData.title)
        edit_create_calendar_description.setText(mData.description)
    }

    /**
     * 获取黄历详情失败时回调
     */
    override fun onDetailLoadFailed() {
        disMissProgressDialog()
    }

    override fun onUpdateCalendarSuccess() {
        disMissProgressDialog()
        (context as CreateCalendarActivity).changeFragmentCallback?.invoke()
        ToastUtil.showToast("修改成功")
    }

    override fun onUpdateCalendarFailed() {
        disMissProgressDialog()
        ToastUtil.showToast("更新失败")
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == REQUEST_CODE && data != null) {
            val resultList = data.getParcelableArrayListExtra<ImageBean>(ImagePicker.INTENT_RESULT_DATA)
            picturePath = resultList[0].imagePath
            Glide.with(this).load(picturePath).placeholder(R.mipmap.ic_launcher).into(img_create_calendar)
            if (!TextUtils.isEmpty(picturePath)) {
                mPresenter?.upLoadPicture(picturePath)
            } else ToastUtil.showToast(R.string.toast_image_load_error)
        }
    }

}