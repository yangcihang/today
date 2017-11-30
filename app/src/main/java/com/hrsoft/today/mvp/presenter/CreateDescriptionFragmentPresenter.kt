package com.hrsoft.today.mvp.presenter

import android.text.TextUtils
import com.hrsoft.today.R
import com.hrsoft.today.mvp.contract.CreateDescriptionContract
import com.hrsoft.today.mvp.model.helper.CreateModelHelper
import com.hrsoft.today.mvp.model.models.CalendarDetailModel
import com.hrsoft.today.mvp.model.models.NewCalendarModel
import com.hrsoft.today.util.ToastUtil

/**
 * @author YangCihang.
 * @since 17/11/24 20:22.
 * email yangcihang@hrsoft.net.
 */
class CreateDescriptionFragmentPresenter(override var mView: CreateDescriptionContract.View?) : CreateDescriptionContract.Presenter {
    //第二个页面的限制数量l
    var stateSum = 0

    override fun onDetach() {
        mView = null
    }

    override fun loadDetail(id: Long) {
        CreateModelHelper.requestCalendarInfo(id.toInt(), this)
    }

    override fun updateCalendar(id: Int, calendarModel: NewCalendarModel) {
        CreateModelHelper.updateCalendarDescription(this, calendarModel, id)
    }

    override fun createNewCalendar(newCalendarModel: NewCalendarModel) {
        newCalendarModel.let {
            if (newCalendarModel.title!!.length !in 5..20) {
                ToastUtil.showToast(R.string.toast_calendar_title_error)
                mView?.onCreateNewCalendarFailed()
                return
            }
            if (newCalendarModel.description!!.length !in 0..500) {
                ToastUtil.showToast(R.string.toast_calendar_description_error)
                mView?.onCreateNewCalendarFailed()
                return
            }

            if (TextUtils.isEmpty(newCalendarModel.picture)) {
                ToastUtil.showToast(R.string.toast_calendar_picture_error)
                mView?.onCreateNewCalendarFailed()
                return
            }
        }
        stateSum = newCalendarModel.goodPick!! + newCalendarModel.badPick!!
        CreateModelHelper.createNewCalendar(this, newCalendarModel)
    }

    override fun upLoadPicture(picturePath: String) {
        CreateModelHelper.getQiNiuToken(picturePath, this)
    }

    override fun upLoadPictureSuccess(netPath: String) {
        mView?.onPictureUploadSuccess(netPath)
    }

    override fun upLoadPictureFailed() {
        mView?.onPictureUploadFailed()
    }

    override fun onCreateNewCalendarSuccess(id: Long) {
        mView?.onCreateNewCalendarSuccess(id, stateSum)
    }

    override fun onCreateNewCalendarFailed() {
        mView?.onCreateNewCalendarFailed()
    }

    override fun onDetailLoaded(mData: CalendarDetailModel) {
        mView?.onDetailLoaded(mData)
    }

    override fun onDetailLoadFailed() {
        mView?.onDetailLoadFailed()
    }

    override fun onUpdateCalendarSuccess() {
        mView?.onUpdateCalendarSuccess()
    }

    override fun onUpdateCalendarFailed() {
        mView?.onUpdateCalendarFailed()
    }
}