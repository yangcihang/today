package com.hrsoft.today.base

import android.app.ProgressDialog
import android.content.pm.ActivityInfo
import android.os.Build
import android.os.Bundle
import android.support.annotation.RequiresApi
import android.support.annotation.StringRes
import android.support.v7.app.AppCompatActivity
import com.hrsoft.today.util.Utility

/**
 * @author YangCihang
 * @since  17/9/23.
 * email yangcihang@hrsoft.net
 */
@RequiresApi(Build.VERSION_CODES.JELLY_BEAN_MR1)
abstract class BaseActivity : AppCompatActivity() {
    protected var progressDialog: ProgressDialog? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //禁止横屏
        requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT
    }

    protected fun init(): Unit {
        initVariable()
        initView()
        loadData()
    }


    protected abstract fun initVariable()
    protected abstract fun initView()
    protected abstract fun loadData()
    protected abstract fun getLayoutId(): Int

    /**
     * 显示progressDialog
     *
     * @param message 显示信息
     */
    protected fun showProgressDialog(message: String) {
        if (progressDialog == null) {
            progressDialog = ProgressDialog(this)
            progressDialog!!.setCanceledOnTouchOutside(false)
        }
        if (!isDestroyed && progressDialog!!.isShowing()) {
            progressDialog!!.dismiss()
        }
        progressDialog!!.setMessage(message)
        Utility.runOnUiThread(Runnable { progressDialog!!.show() })
    }

    /**
     * 显示progressDialog
     *
     * @param resId 显示信息资源ID
     */
    protected fun showProgressDialog(@StringRes resId: Int) {
        val message = getString(resId)
        showProgressDialog(message)
    }

    /**
     * 取消ProgressDialog
     */
    protected fun disMissProgressDialog() {
        if (!isDestroyed && progressDialog!!.isShowing) {
            Utility.runOnUiThread(Runnable { progressDialog!!.dismiss() }, 300)
        }
    }

    override fun onDestroy() {
        if (progressDialog != null && progressDialog!!.isShowing) {
            progressDialog!!.dismiss()
        }
        super.onDestroy()
    }

}