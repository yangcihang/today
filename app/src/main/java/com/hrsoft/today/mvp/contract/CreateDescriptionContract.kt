package com.hrsoft.today.mvp.contract

import com.hrsoft.today.base.BaseContract
import com.hrsoft.today.mvp.model.models.CalendarDetailModel
import com.hrsoft.today.mvp.model.models.CalendarRecommendModel
import com.hrsoft.today.mvp.model.models.CalendarStateItemModel
import com.hrsoft.today.mvp.model.models.NewCalendarModel

/**
 * @author YangCihang.
 * @since 17/11/24 20:14.
 * email yangcihang@hrsoft.net.
 */
interface CreateDescriptionContract {
    interface View : BaseContract.View<Presenter> {
        fun onCreateNewCalendarSuccess(id: Long, stateNum: Int)
        fun onCreateNewCalendarFailed()
        fun onPictureUploadSuccess(netpath: String)
        fun onPictureUploadFailed()
        fun onDetailLoaded(mData: CalendarDetailModel)
        fun onDetailLoadFailed()
        fun onUpdateCalendarSuccess()
        fun onUpdateCalendarFailed()
    }

    interface Presenter : BaseContract.Presenter<View> {
        fun createNewCalendar(newCalendarModel: NewCalendarModel)
        fun upLoadPicture(picturePath: String)
        fun upLoadPictureSuccess(netPath: String)
        fun upLoadPictureFailed()
        fun onCreateNewCalendarFailed()
        fun onCreateNewCalendarSuccess(id: Long)
        fun loadDetail(id: Long)
        fun onDetailLoaded(mData: CalendarDetailModel)
        fun onDetailLoadFailed()
        fun onUpdateCalendarSuccess()
        fun onUpdateCalendarFailed()
        fun updateCalendar(id: Int, calendarModel: NewCalendarModel)
    }
}