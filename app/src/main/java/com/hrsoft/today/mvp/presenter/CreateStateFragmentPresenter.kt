package com.hrsoft.today.mvp.presenter

import com.hrsoft.today.mvp.contract.CreateStateContract
import com.hrsoft.today.mvp.model.helper.CreateModelHelper
import com.hrsoft.today.mvp.model.models.CalendarStateItemModel
import com.hrsoft.today.util.ToastUtil

/**
 * @author YangCihang.
 * @since 17/11/24 20:57.
 * email yangcihang@hrsoft.net.
 */
class CreateStateFragmentPresenter(override var mView: CreateStateContract.View?) : CreateStateContract.Presenter {


    override fun onDetach() {
        mView = null
    }

    override fun getStateDetail(id: Int) {
        CreateModelHelper.getStateDetail(this, id)
    }

    override fun updateStateModel(id: Long, stateList: List<CalendarStateItemModel>, stateSum: Int) {
        //TODO(检验List)
        if (stateList.size < stateSum) {
            ToastUtil.showToast("请创建至少$stateSum 个action")
            mView?.updateStateModelFailed()
            return
        }
        CreateModelHelper.updateStateModel(this, id, stateList)
    }

    override fun updateStateModelSuccess() {
        mView?.updateStateModelSuccess()
    }

    override fun updateStateModelFailed() {
        mView?.updateStateModelFailed()
    }

    override fun onStateLoadSuccess(stateList: List<CalendarStateItemModel>) {
        mView?.onStateLoadSuccess(stateList)
    }

    override fun onStateLoadFailed() {
        mView?.onStateLoadFailed()
    }
}