package com.hrsoft.today.mvp.contract

import com.hrsoft.today.base.BaseContract
import com.hrsoft.today.mvp.model.models.CalendarRecommendModel

/**
 * @author YangCihang.
 * @since 17/11/24 20:15.
 * email yangcihang@hrsoft.net.
 */
interface CreateRecommendContract {
    interface View : BaseContract.View<Presenter> {
        fun onCreateRecommendSuccess()
        fun onCreateRecommendFailed()
        fun onRecommendDetailLoadSuccess(recommendList: List<CalendarRecommendModel>)
        fun onRecommendDetailLoadFailed()
    }

    interface Presenter : BaseContract.Presenter<View> {
        fun updateRecommendModel(id: Int, recommendList: List<CalendarRecommendModel>)
        fun onCreateRecommendSuccess()
        fun onCreateRecommendFailed()
        fun getRecommendDetail(id: Int)
        fun onRecommendDetailLoadSuccess(recommendList: List<CalendarRecommendModel>)
        fun onRecommendDetailLoadFailed()
    }
}