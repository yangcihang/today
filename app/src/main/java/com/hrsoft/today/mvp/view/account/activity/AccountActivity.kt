package com.hrsoft.today.mvp.view.account.activity

import android.content.Intent
import android.text.TextUtils
import com.hrsoft.today.R
import com.hrsoft.today.base.NoBarActivity
import com.hrsoft.today.mvp.model.User
import com.hrsoft.today.mvp.view.account.fragment.LoginFragment
import com.hrsoft.today.mvp.view.account.fragment.RegisterFragment
import com.hrsoft.today.mvp.view.main.activity.MainActivity
import com.hrsoft.today.mvp.view.square.activity.SquareActivity
import com.hrsoft.today.util.FragmentUtil
import kotlinx.android.synthetic.main.activity_account.*

/**
 * @author YangCihang.
 * @since 17/11/17 21:04.
 * email yangcihang@hrsoft.net.
 */
class AccountActivity : NoBarActivity() {
    private var loginFragment: LoginFragment = LoginFragment()
    private var registerFragment: RegisterFragment = RegisterFragment()
    override fun initVariable() {
        //TODO(splash页面判断)
        if (User.isLogin()) {
            toMain()
        }
        loginFragment.apply {
            registerListener = {
                supportFragmentManager
                        .beginTransaction()
                        .addToBackStack(null)
                        .replace(R.id.frame_account, registerFragment)
                        .commit()
            }
            onLoginSuccessListener = { toMain() }
        }
    }

    override fun initView() {
        FragmentUtil.replace(this, R.id.frame_account, loginFragment, null)
    }

    private fun toMain() {
        this@AccountActivity.finish()
        startActivity(Intent(this@AccountActivity, MainActivity::class.java))
    }

    override fun loadData() {
    }

    override fun getLayoutId(): Int {
        return R.layout.activity_account
    }

}