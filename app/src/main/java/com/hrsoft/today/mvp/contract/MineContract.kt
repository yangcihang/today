package com.hrsoft.today.mvp.contract

import com.hrsoft.today.base.BaseContract
import com.hrsoft.today.mvp.model.RegisterRequestModel

/**
 * @author YangCihang.
 * @since 17/11/23 20:50.
 * email yangcihang@hrsoft.net.
 */
interface MineContract {
    interface View : BaseContract.View<Presenter> {
        fun onPictureUploadSuccess(data: String)
        fun onPictureUploadFailed()
    }

    interface Presenter : BaseContract.Presenter<View> {
        fun upLoadPicture(picturePath: String)
        fun updateSign(content: String)
        fun onPictureUploadSuccess()
        fun onPictureUploadFailed()
    }
}