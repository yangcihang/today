package com.hrsoft.today.mvp.contract

import com.hrsoft.today.base.BaseContract
import com.hrsoft.today.mvp.model.CalendarStateItemModel
import com.hrsoft.today.mvp.model.NewCalendarModel
import com.hrsoft.today.mvp.model.NewCalendarRecommendModel

/**
 * @author YangCihang
 * @since  17/11/6.
 * email yangcihang@hrsoft.net
 */
interface CreateContract {
    interface View : BaseContract.View<Presenter> {
        fun onCreateNewCalendarSuccess()
        fun onCreateStateModelSuccess()
        fun onCreateNewCalendarFailed()
        fun onCreateStateModelFailed()
    }

    interface Presenter : BaseContract.Presenter<View> {
        fun createNewCalendar(newCalendarModel: NewCalendarModel)
        fun createStateModel(id: Int, stateList: List<CalendarStateItemModel>)
        fun createRecommendModel(id: Int, recommendList: List<NewCalendarRecommendModel>)
    }
}
