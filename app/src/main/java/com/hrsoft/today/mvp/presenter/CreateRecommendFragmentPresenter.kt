package com.hrsoft.today.mvp.presenter

import com.hrsoft.today.R
import com.hrsoft.today.mvp.contract.CreateRecommendContract
import com.hrsoft.today.mvp.model.helper.CreateModelHelper
import com.hrsoft.today.mvp.model.models.CalendarRecommendModel
import com.hrsoft.today.util.ToastUtil

/**
 * @author YangCihang.
 * @since 17/11/24 21:06.
 * email yangcihang@hrsoft.net.
 */
class CreateRecommendFragmentPresenter(override var mView: CreateRecommendContract.View?) : CreateRecommendContract.Presenter {
    override fun getRecommendDetail(id: Int) {
        CreateModelHelper.getRecommendDetail(this, id)
    }


    override fun onDetach() {
        mView = null
    }

    override fun updateRecommendModel(id: Int, recommendList: List<CalendarRecommendModel>) {
        if (recommendList.isEmpty()) {
            ToastUtil.showToast(R.string.toast_recommend_item_empty)
            mView?.onCreateRecommendFailed()
            return
        }
        CreateModelHelper.updateNewRecommendModel(this, id, recommendList)
    }

    override fun onCreateRecommendSuccess() {
        mView?.onCreateRecommendSuccess()
    }

    override fun onCreateRecommendFailed() {
        mView?.onCreateRecommendFailed()
    }

    override fun onRecommendDetailLoadSuccess(recommendList: List<CalendarRecommendModel>) {
        mView?.onRecommendDetailLoadSuccess(recommendList)
    }

    override fun onRecommendDetailLoadFailed() {
        mView?.onRecommendDetailLoadFailed()
    }

}