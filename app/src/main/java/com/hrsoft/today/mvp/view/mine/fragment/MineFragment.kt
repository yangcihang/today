package com.hrsoft.today.mvp.view.mine.fragment

import android.text.InputType
import android.text.TextUtils
import com.hrsoft.today.App
import com.hrsoft.today.R
import com.hrsoft.today.base.BaseFragment
import com.hrsoft.today.mvp.contract.MineContract
import com.hrsoft.today.mvp.contract.MineFragmentContract
import com.hrsoft.today.mvp.model.User
import com.hrsoft.today.mvp.presenter.MineFragmentPresenter
import com.hrsoft.today.mvp.view.mine.activity.MineActivity
import com.hrsoft.today.util.DialogUtils
import com.hrsoft.today.util.ToastUtil
import kotlinx.android.synthetic.main.fragment_mine.*

/**
 * @author YangCihang.
 * @since 17/11/23 21:35.
 * email yangcihang@hrsoft.net.
 */
class MineFragment : BaseFragment(), MineFragmentContract.View {
    override var mPresenter: MineFragmentContract.Presenter? = MineFragmentPresenter(this)
    override fun getLayoutId(): Int = R.layout.fragment_mine
    private var newSign = ""
    var onUpdatePswClickedListener: (() -> Unit)? = null
    override fun initVariable() {
    }

    override fun initView() {
        ll_sign.setOnClickListener {
            DialogUtils(context).setInputHintText(getString(R.string.dialog_input_user_sign))
                    .setCancelable(false)
                    .setInputEditView(InputType.TYPE_TEXT_VARIATION_PASSWORD)
                    .setNegativeButton {}
                    .setPositiveButton {
                        mPresenter?.updateSign(it.inputText)
                        newSign = it.inputText
                    }
                    .showAlertDialog()
        }
        ll_psw.setOnClickListener {
            onUpdatePswClickedListener?.invoke()
        }
        txt_logout.setOnClickListener { App.instance.toLogin() }
    }

    override fun loadData() {
    }

    override fun updateSignSuccess() {
        ToastUtil.showToast(R.string.toast_update_sign_success)
        User.signature = newSign
        User.saveUserInfo()
        (activity as MineActivity).onUpdateSignCallback.invoke(newSign)
    }

    override fun updateSignFailed() {
        ToastUtil.showToast(R.string.toast_update_sign_failed)
    }

    override fun onDestroy() {
        super.onDestroy()
        mPresenter?.onDetach()
    }
}