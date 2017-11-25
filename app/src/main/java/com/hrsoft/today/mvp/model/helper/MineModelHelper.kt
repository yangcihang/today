package com.hrsoft.today.mvp.model.helper

import com.hrsoft.today.App
import com.hrsoft.today.mvp.model.models.MineUserModel
import com.hrsoft.today.mvp.presenter.MineActivityPresenter
import com.hrsoft.today.mvp.presenter.MineFragmentPresenter
import com.hrsoft.today.mvp.presenter.MineUpdateFragmentPresenter
import com.hrsoft.today.network.NetWork
import com.hrsoft.today.network.RspCallback
import java.util.*

/**
 * @author YangCihang.
 * @since 17/11/23 20:55.
 * email yangcihang@hrsoft.net.
 */
object MineModelHelper {
    fun updateUserAvatar(callback: MineActivityPresenter, key: String) {
        NetWork.getService().updateAvatar(MineUserModel(avatar = key)).enqueue(object : RspCallback<String>() {
            override fun onSuccess(data: String?) {
                callback.onUploadPictureSuccess(data!!)
            }

            override fun onFailed() {
                callback.onUploadPictureFailed()
            }

        })
    }

    fun getQiNiuToken(picturePath: String, callback: MineActivityPresenter): Unit {
        NetWork.getService().getToken().enqueue(object : RspCallback<String>() {
            override fun onSuccess(data: String?) {
                App.instance.uploadManager.put(picturePath, UUID.randomUUID().toString(), data
                        , { key, info, res ->
                    if (info.isOK) callback.onGetTokenSuccess(key) else callback.onUploadPictureFailed()
                }, null)
            }

            override fun onFailed() {
                callback.onUploadPictureFailed()
            }

        })
    }

    fun updateUserSign(callback: MineFragmentPresenter, date: String) {
        NetWork.getService().updateSignature(MineUserModel(signature = date)).enqueue(object : RspCallback<Unit>() {
            override fun onSuccess(data: Unit?) {
                callback.updateSignSuccess()
            }

            override fun onFailed() {
                callback.updateSignFailed()
            }

        })
    }

    fun updatePsw(callback: MineUpdateFragmentPresenter, model: MineUserModel) {
        NetWork.getService().updatePsw(model = model).enqueue(object : RspCallback<Unit>() {
            override fun onSuccess(data: Unit?) {
                callback.updatePswSuccess()
            }

            override fun onFailed() {
                callback.updatePswFailed()
            }

        })
    }
}