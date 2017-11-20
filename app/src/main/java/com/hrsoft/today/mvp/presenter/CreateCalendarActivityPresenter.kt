package com.hrsoft.today.mvp.presenter

import android.text.TextUtils
import com.hrsoft.today.R
import com.hrsoft.today.mvp.contract.CreateContract
import com.hrsoft.today.mvp.model.CalendarRecommendModel
import com.hrsoft.today.mvp.model.CalendarStateItemModel
import com.hrsoft.today.mvp.model.NewCalendarModel
import com.hrsoft.today.mvp.model.helper.CreateModelHelper
import com.hrsoft.today.util.ToastUtil

/**
 * @author YangCihang
 * @since  17/11/6.
 * email yangcihang@hrsoft.net
 */
class CreateCalendarActivityPresenter(override var mView: CreateContract.View?) : CreateContract.Presenter {
    private var stateSum = 0
    override fun upLoadPicture(picturePath: String) {
        CreateModelHelper.getQiNiuToken(picturePath, this)
    }

    /**
     * 创建新黄历
     */
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

    /**
     * 创建状态
     */
    override fun createStateModel(id: Long, stateList: List<CalendarStateItemModel>) {
        //TODO(检验List)
        if (stateList.size < stateSum) {
            ToastUtil.showToast("请创建至少$stateSum 个action")
            mView?.onCreateStateModelFailed()
            return
        }
        CreateModelHelper.createStateModel(this, id, stateList)
    }

    /**
     * 创建每日推荐
     */
    override fun createRecommendModel(id: Int, recommendList: List<CalendarRecommendModel>) {
        if (recommendList.isEmpty()) {
            ToastUtil.showToast(R.string.toast_recommend_item_empty)
            mView?.onCreateRecommendFailed()
            return
        }
        CreateModelHelper.createNewRecommendModel(this, id, recommendList)
    }

    override fun onDetach() {
        mView = null
    }

    fun onUploadPictureSuccess(netPath: String) {
        mView?.onPictureUploadSuccess(netPath)
    }

    fun onUploadPictureFailed() {
        mView?.onCreateNewCalendarFailed()
        ToastUtil.showToast("上传失败")
    }

    fun onCreateNewCalendarSuccess(id: Long) {
        mView?.onCreateNewCalendarSuccess(id)
    }

    fun onCreateStateModelSuccess() {
        mView?.onCreateStateModelSuccess()
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