package com.hrsoft.today.mvp.presenter

import android.widget.Toast
import com.hrsoft.today.R
import com.hrsoft.today.mvp.contract.MineUpdateContract
import com.hrsoft.today.mvp.model.MineUserModel
import com.hrsoft.today.mvp.model.helper.MineModelHelper
import com.hrsoft.today.util.ToastUtil

/**
 * @author YangCihang.
 * @since 17/11/24 16:29.
 * email yangcihang@hrsoft.net.
 */
class MineUpdateFragmentPresenter(override var mView: MineUpdateContract.View?) : MineUpdateContract.Presenter {
    override fun onDetach() {
        mView = null
    }

    override fun updatePsw(model: MineUserModel) {
        when {
            model.oldPassword.length !in 6..20 -> {
                ToastUtil.showToast(R.string.toast_old_psw_error)
                return
            }
            model.newPassword.length !in 6..20 -> {
                ToastUtil.showToast(R.string.toast_password_error)
                return
            }
            model.newPassword != model.confirmPassword -> {
                ToastUtil.showToast(R.string.toast_password_confirm_error)
                return
            }
        }
        MineModelHelper.updatePsw(this, model)
    }

    override fun updatePswSuccess() {
        mView?.updatePswSuccess()
    }

    override fun updatePswFailed() {
        mView?.updatePswFailed()
    }
}