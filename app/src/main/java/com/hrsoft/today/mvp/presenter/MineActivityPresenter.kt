package com.hrsoft.today.mvp.presenter

import com.hrsoft.today.mvp.contract.MineContract
import com.hrsoft.today.mvp.model.helper.MineModelHelper
import com.hrsoft.today.util.ToastUtil

/**
 * @author YangCihang.
 * @since 17/11/23 20:52.
 * email yangcihang@hrsoft.net.
 */
class MineActivityPresenter(override var mView: MineContract.View?) : MineContract.Presenter {
    override fun upLoadPicture(picturePath: String) {
        MineModelHelper.getQiNiuToken(picturePath, this)
    }

    override fun updateSign(content: String) {

    }

    override fun onPictureUploadSuccess() {
    }

    override fun onPictureUploadFailed() {
    }

    fun onGetTokenSuccess(key: String) {
        MineModelHelper.updateUserAvatar(this, key)
    }

    fun onUploadPictureSuccess(data: String) {
        mView?.onPictureUploadSuccess(data)
    }

    fun onUploadPictureFailed() {
        mView?.onPictureUploadFailed()
        ToastUtil.showToast("上传失败")
    }


    override fun onDetach() {
        mView = null
    }
}
