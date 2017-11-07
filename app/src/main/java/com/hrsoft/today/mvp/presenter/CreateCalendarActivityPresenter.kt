package com.hrsoft.today.mvp.presenter

import com.hrsoft.today.mvp.contract.CreateContract
import com.hrsoft.today.mvp.model.CalendarStateItemModel
import com.hrsoft.today.mvp.model.NewCalendarModel
import com.hrsoft.today.mvp.model.NewCalendarRecommendModel
import com.hrsoft.today.mvp.model.helper.CreateModelHelper

/**
 * @author YangCihang
 * @since  17/11/6.
 * email yangcihang@hrsoft.net
 */
class CreateCalendarActivityPresenter(override var mView: CreateContract.View?) : CreateContract.Presenter {
    override fun createNewCalendar(newCalendarModel: NewCalendarModel) {
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

    fun onCreateNewCalendarSuccess() {

    }

    fun onCreateStateModelSuccess() {

    }

    fun onCreateNewCalendarFailed() {

    }

    fun onCreateStateModelFailed() {

    }

    fun onCreateRecommendSuccess() {

    }

    fun onCreateRecommendFailed() {

    }
}