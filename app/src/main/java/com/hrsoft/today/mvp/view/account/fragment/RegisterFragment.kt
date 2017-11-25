package com.hrsoft.today.mvp.view.account.fragment

import com.hrsoft.today.R
import com.hrsoft.today.base.BaseFragment
import com.hrsoft.today.mvp.contract.RegisterContract
import com.hrsoft.today.mvp.model.models.RegisterRequestModel
import com.hrsoft.today.mvp.presenter.RegisterFragmentPresenter
import kotlinx.android.synthetic.main.fragment_register.*

/**
 * @author YangCihang.
 * @since 17/11/17 21:12.
 * email yangcihang@hrsoft.net.
 */
class RegisterFragment : BaseFragment(), RegisterContract.View {
    override var mPresenter: RegisterContract.Presenter? = RegisterFragmentPresenter(this)

    override fun getLayoutId(): Int {
        return R.layout.fragment_register
    }

    override fun initVariable() {
    }

    override fun initView() {
        btn_register.setOnClickListener {
            val model = RegisterRequestModel(name = edit_register_nickname.text.toString().trim()
                    , password = edit_register_psw.text.toString().trim()
                    , confirmPassword = edit_register_confirm_psw.text.toString().trim()
                    , signature = edit_register_sign.text.toString().trim())
            showProgressDialog(R.string.dialog_wait)
            mPresenter?.register(model)
        }
    }

    override fun loadData() {
    }

    override fun registerSuccess() {
        disMissProgressDialog()
        fragmentManager.popBackStack()
    }

    override fun registerFailed() {
        disMissProgressDialog()
    }

    override fun onDestroy() {
        super.onDestroy()
        mPresenter?.onDetach()
    }

}