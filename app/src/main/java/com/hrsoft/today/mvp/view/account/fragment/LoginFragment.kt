package com.hrsoft.today.mvp.view.account.fragment

import com.hrsoft.today.R
import com.hrsoft.today.base.BaseFragment
import com.hrsoft.today.mvp.contract.LoginContract
import com.hrsoft.today.mvp.model.LoginRequestModel
import com.hrsoft.today.mvp.presenter.LoginFragmentPresenter
import kotlinx.android.synthetic.main.fragment_login.*

/**
 * @author YangCihang.
 * @since 17/11/17 21:09.
 * email yangcihang@hrsoft.net.
 */
class LoginFragment : BaseFragment(), LoginContract.View {
    override var mPresenter: LoginContract.Presenter? = LoginFragmentPresenter(this)
    lateinit var registerListener: (() -> Unit)
    lateinit var onLoginSuccessListener: (() -> Unit)

    override fun getLayoutId(): Int {
        return R.layout.fragment_login
    }

    override fun initVariable() {
    }

    override fun initView() {
        txt_login_register.setOnClickListener { registerListener.invoke() }
        btn_login.setOnClickListener {
            val model = LoginRequestModel(name = edit_login_nickname.text.toString().trim()
                    , password = edit_login_psw.text.toString().trim())
            showProgressDialog(R.string.dialog_wait)
            mPresenter?.login(model)
        }
    }

    override fun loadData() {
    }

    override fun loginSuccess() {
        disMissProgressDialog()
        onLoginSuccessListener.invoke()
    }

    override fun loginFailed() {
        disMissProgressDialog()
    }

    override fun onDestroy() {
        super.onDestroy()
        mPresenter?.onDetach()
    }

}