package com.hrsoft.today.mvp.presenter

import com.hrsoft.today.R
import com.hrsoft.today.mvp.contract.CreateContract
import com.hrsoft.today.mvp.model.CalendarStateItemModel
import com.hrsoft.today.mvp.model.NewCalendarModel
import com.hrsoft.today.mvp.model.NewCalendarRecommendModel
import com.hrsoft.today.mvp.model.helper.CreateModelHelper
import com.hrsoft.today.util.ToastUtil

/**
 * @author YangCihang
 * @since  17/11/6.
 * email yangcihang@hrsoft.net
 */
class CreateCalendarActivityPresenter(override var mView: CreateContract.View?) : CreateContract.Presenter {
    override fun upLoadPicture(picturePath: String) {
        CreateModelHelper.getQiNiuToken(picturePath, this)
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
        }
        CreateModelHelper.createNewCalendar(this, newCalendarModel)
    }

    override fun createStateModel(id: Int, stateList: List<CalendarStateItemModel>) {
        //TODO(检验List)
        CreateModelHelper.createStateModel(this, id, stateList)
    }

    override fun createRecommendModel(id: Int, recommendList: List<NewCalendarRecommendModel>) {
        CreateModelHelper.createNewRecommendModel(this, id, recommendList)
    }

    override fun onDetach() {
        mView = null
    }

    fun onUploadPictureSuccess(netPath: String) {
        mView?.onPictureUploadSuccess(netPath)
    }

    fun onUploadPictureFailed() {
        mView?.onCreateRecommendFailed()
        ToastUtil.showToast("上传失败")
    }

    fun onCreateNewCalendarSuccess() {
        mView?.onCreateNewCalendarSuccess()
    }

    fun onCreateStateModelSuccess() {
        mView?.onCreateStateModelFailed()
    }

    fun onCreateNewCalendarFailed() {
        mView?.onCreateNewCalendarFailed()
    }

    fun onCreateStateModelFailed() {
        mView?.onCreateStateModelFailed()
    }

    fun onCreateRecommendSuccess() {
        mView?.onCreateRecommendSuccess()
    }

    fun onCreateRecommendFailed() {
        mView?.onCreateRecommendFailed()
    }
}