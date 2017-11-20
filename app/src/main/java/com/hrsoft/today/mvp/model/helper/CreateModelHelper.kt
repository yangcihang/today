package com.hrsoft.today.mvp.model.helper

import com.hrsoft.today.App
import com.hrsoft.today.mvp.model.CalendarRecommendModel
import com.hrsoft.today.mvp.model.CalendarStateItemModel
import com.hrsoft.today.mvp.model.NewCalendarModel
import com.hrsoft.today.mvp.presenter.CreateCalendarActivityPresenter
import com.hrsoft.today.network.NetWork
import com.hrsoft.today.network.RspCallback
import java.util.*

/**
 * @author YangCihang
 * @since  17/11/6.
 * email yangcihang@hrsoft.net
 */
object CreateModelHelper {
    fun createNewCalendar(callback: CreateCalendarActivityPresenter, model: NewCalendarModel) {
        NetWork.getService().createNewCalendar(model).enqueue(object : RspCallback<Long>() {
            override fun onSuccess(data: Long?) {
                callback.onCreateNewCalendarSuccess(data!!)
            }

            override fun onFailed() {
                callback.onCreateNewCalendarFailed()
            }
        })
    }

    fun createStateModel(callback: CreateCalendarActivityPresenter, id: Long, model: List<CalendarStateItemModel>) {
        NetWork.getService().createCalendarStates(id, model).enqueue(object : RspCallback<Long>() {
            override fun onSuccess(data: Long?) {
                callback.onCreateStateModelSuccess()
            }

            override fun onFailed() {
                callback.onCreateStateModelFailed()
            }


        })
    }

    fun createNewRecommendModel(callback: CreateCalendarActivityPresenter, id: Int, model: List<CalendarRecommendModel>) {
        NetWork.getService().createCalendarRecommend(id, model).enqueue(object : RspCallback<Long>() {
            override fun onSuccess(data: Long?) {
                callback.onCreateRecommendSuccess()
            }

            override fun onFailed() {
                callback.onCreateRecommendFailed()
            }

        })
    }

    fun getQiNiuToken(picturePath: String, callback: CreateCalendarActivityPresenter): Unit {
        NetWork.getService().getToken().enqueue(object : RspCallback<String>() {
            override fun onSuccess(data: String?) {
                App.instance.uploadManager.put(picturePath, UUID.randomUUID().toString(), data
                        , { key, info, res ->
                    if (info.isOK) callback.onUploadPictureSuccess(key) else callback.onCreateRecommendFailed()
                }, null)
            }

            override fun onFailed() {
                callback.onUploadPictureFailed()
            }

        })
    }
}