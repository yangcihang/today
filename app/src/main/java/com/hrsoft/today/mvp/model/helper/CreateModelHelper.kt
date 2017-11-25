package com.hrsoft.today.mvp.model.helper

import com.hrsoft.today.App
import com.hrsoft.today.mvp.model.models.CalendarDetailModel
import com.hrsoft.today.mvp.model.models.CalendarRecommendModel
import com.hrsoft.today.mvp.model.models.CalendarStateItemModel
import com.hrsoft.today.mvp.model.models.NewCalendarModel
import com.hrsoft.today.mvp.presenter.CreateDescriptionFragmentPresenter
import com.hrsoft.today.mvp.presenter.CreateRecommendFragmentPresenter
import com.hrsoft.today.mvp.presenter.CreateStateFragmentPresenter
import com.hrsoft.today.mvp.presenter.DetailActivityPresenter
import com.hrsoft.today.network.NetWork
import com.hrsoft.today.network.RspCallback
import java.util.*

/**
 * @author YangCihang
 * @since  17/11/6.
 * email yangcihang@hrsoft.net
 */
object CreateModelHelper {
    fun createNewCalendar(callback: CreateDescriptionFragmentPresenter, model: NewCalendarModel) {
        NetWork.getService().createNewCalendar(model).enqueue(object : RspCallback<Long>() {
            override fun onSuccess(data: Long?) {
                callback.onCreateNewCalendarSuccess(data!!)
            }

            override fun onFailed() {
                callback.onCreateNewCalendarFailed()
            }
        })
    }

    fun updateStateModel(callback: CreateStateFragmentPresenter, id: Long, model: List<CalendarStateItemModel>) {
        NetWork.getService().updateCalendarStates(id, model).enqueue(object : RspCallback<Long>() {
            override fun onSuccess(data: Long?) {
                callback.updateStateModelSuccess()
            }

            override fun onFailed() {
                callback.updateStateModelFailed()
            }


        })
    }

    fun updateNewRecommendModel(callback: CreateRecommendFragmentPresenter, id: Int, model: List<CalendarRecommendModel>) {
        NetWork.getService().updateCalendarRecommend(id, model).enqueue(object : RspCallback<Long>() {
            override fun onSuccess(data: Long?) {
                callback.onCreateRecommendSuccess()
            }

            override fun onFailed() {
                callback.onCreateRecommendFailed()
            }

        })
    }

    /**
     * 获取七牛token
     */
    fun getQiNiuToken(picturePath: String, callback: CreateDescriptionFragmentPresenter) {
        NetWork.getService().getToken().enqueue(object : RspCallback<String>() {
            override fun onSuccess(data: String?) {
                App.instance.uploadManager.put(picturePath, UUID.randomUUID().toString(), data
                        , { key, info, res ->
                    if (info.isOK) callback.upLoadPictureSuccess(key) else callback.upLoadPictureFailed()
                }, null)
            }

            override fun onFailed() {
                callback.upLoadPictureFailed()
            }

        })
    }

    /**
     * 获取黄历详情信息
     */
    fun requestCalendarInfo(calendarId: Int, callback: CreateDescriptionFragmentPresenter) {
        NetWork.getService().requestCalendarDetail(calendarId).enqueue(object : RspCallback<CalendarDetailModel>() {
            override fun onSuccess(data: CalendarDetailModel?) {
                callback.onDetailLoaded(data!!)
            }

            override fun onFailed() {
                callback.onDetailLoadFailed()
            }
        })
    }

    fun updateCalendarDescription(callback: CreateDescriptionFragmentPresenter, model: NewCalendarModel, id: Int) {
        NetWork.getService().updateCalendarDescription(model = model, id = id).enqueue(object : RspCallback<Unit>() {
            override fun onSuccess(data: Unit?) {
                callback.onUpdateCalendarSuccess()
            }

            override fun onFailed() {
                callback.onUpdateCalendarFailed()
            }

        })
    }

    fun getStateDetail(callback: CreateStateFragmentPresenter, id: Int) {
        NetWork.getService().getCalendarStateInfo(id).enqueue(object : RspCallback<List<CalendarStateItemModel>>() {
            override fun onSuccess(data: List<CalendarStateItemModel>?) {
                callback.onStateLoadSuccess(data!!)
            }

            override fun onFailed() {
                callback.onStateLoadFailed()
            }
        })
    }

    fun getRecommendDetail(callback: CreateRecommendFragmentPresenter, id: Int) {
        NetWork.getService().getCalendarRecommendInfo(id).enqueue(object : RspCallback<List<CalendarRecommendModel>>() {
            override fun onSuccess(data: List<CalendarRecommendModel>?) {
                callback.onRecommendDetailLoadSuccess(data!!)
            }

            override fun onFailed() {
                callback.onRecommendDetailLoadFailed()
            }

        })
    }
}