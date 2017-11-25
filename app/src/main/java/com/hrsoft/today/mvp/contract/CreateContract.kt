package com.hrsoft.today.mvp.contract

import com.hrsoft.today.base.BaseContract
import com.hrsoft.today.mvp.model.models.CalendarRecommendModel
import com.hrsoft.today.mvp.model.models.CalendarStateItemModel
import com.hrsoft.today.mvp.model.models.NewCalendarModel

/**
 * @author YangCihang
 * @since  17/11/6.
 * email yangcihang@hrsoft.net
 */
interface CreateContract {
    interface View : BaseContract.View<Presenter> {
        fun onCreateNewCalendarSuccess(id: Long)
        fun onCreateStateModelSuccess()
        fun onCreateRecommendSuccess()
        fun onCreateRecommendFailed()
        fun onCreateNewCalendarFailed()
        fun onCreateStateModelFailed()
        fun onPictureUploadSuccess(path: String)
        fun onPictureUploadFailed()
    }

    interface Presenter : BaseContract.Presenter<View> {
        fun createNewCalendar(newCalendarModel: NewCalendarModel)
        fun createStateModel(id: Long, stateList: List<CalendarStateItemModel>)
        fun createRecommendModel(id: Int, recommendList: List<CalendarRecommendModel>)
        fun upLoadPicture(picturePath: String)
    }
}
