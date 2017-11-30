package com.hrsoft.today.mvp.view.mine.fragment

import com.hrsoft.today.R
import com.hrsoft.today.base.BaseFragment
import com.hrsoft.today.mvp.contract.MineUpdateContract
import com.hrsoft.today.mvp.model.models.MineUserModel
import com.hrsoft.today.mvp.presenter.MineUpdateFragmentPresenter
import com.hrsoft.today.util.ToastUtil
import kotlinx.android.synthetic.main.fragment_update_psw.*

/**
 * @author YangCihang.
 * @since 17/11/23 21:36.
 * email yangcihang@hrsoft.net.
 */
class UpdatePswFragment : BaseFragment(), MineUpdateContract.View {
    override var mPresenter: MineUpdateContract.Presenter? = MineUpdateFragmentPresenter(this)
    var onUpdatePswSuccessListener: (() -> Unit)? = null

    override fun getLayoutId(): Int {
        return R.layout.fragment_update_psw
    }

    override fun initVariable() {
    }

    override fun initView() {
        txt_confirm.setOnClickListener {
            mPresenter?.updatePsw(MineUserModel(oldPassword = edit_old_psw.text.toString().trim()
                    , confirmPassword = edit_confirm_psw.text.toString().trim()
                    , newPassword = edit_new_psw.text.toString().trim()))
        }
    }

    override fun loadData() {
    }

    override fun updatePswSuccess() {
        ToastUtil.showToast("修改成功")
        onUpdatePswSuccessListener?.invoke()
    }

    override fun updatePswFailed() {
    }

    override fun onDestroy() {
        super.onDestroy()
        mPresenter?.onDetach()
    }

}