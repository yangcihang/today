package com.hrsoft.today.mvp.contract

import com.hrsoft.today.base.BaseContract
import com.hrsoft.today.mvp.model.models.CalendarRecommendModel
import com.hrsoft.today.mvp.model.models.CalendarStateItemModel
import com.hrsoft.today.mvp.model.models.NewCalendarModel

/**
 * @author YangCihang.
 * @since 17/11/24 20:15.
 * email yangcihang@hrsoft.net.
 */
interface CreateStateContract {
    interface View : BaseContract.View<Presenter> {
        fun updateStateModelSuccess()
        fun updateStateModelFailed()
        fun onStateLoadSuccess(stateList: List<CalendarStateItemModel>)
        fun onStateLoadFailed()
    }

    interface Presenter : BaseContract.Presenter<View> {
        fun updateStateModel(id: Long, stateList: List<CalendarStateItemModel>, stateSum: Int)
        fun updateStateModelSuccess()
        fun updateStateModelFailed()
        fun getStateDetail(id: Int)
        fun onStateLoadSuccess(stateList: List<CalendarStateItemModel>)
        fun onStateLoadFailed()
    }
}