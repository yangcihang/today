package com.hrsoft.today.base

import android.app.ProgressDialog
import android.content.Context
import android.os.Bundle
import android.support.annotation.StringRes
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.hrsoft.today.util.Utility


/**
 * @author YangCihang
 * @since  17/10/31.
 * email yangcihang@hrsoft.net
 */
abstract class BaseFragment : Fragment() {
    /** 进度窗  */
    protected lateinit var progressDialog: ProgressDialog
    /** 当前Fragment RootView  */
    /**
     * 获取当前Fragment的RootView

     * @return RootView
     */
    protected var rootView: View? = null
        private set

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        rootView = inflater!!.inflate(getLayoutId(), container, false)
        return rootView
    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        init()
    }


    /**
     * 页面初始化操作.
     */
    protected fun init() {
        preInit()
        initVariable()
        initView()
        loadData()
    }

    /**
     * 执行init 方法之前的处理
     */
    private fun preInit() {
        // progressDialog 统一定义
        progressDialog = ProgressDialog(activity)
        progressDialog.setCanceledOnTouchOutside(false)
    }

    /**
     * 获取LayoutId.

     * @return LayoutId 布局文件Id
     */
    protected abstract fun getLayoutId(): Int

    /**
     * 初始化变量.
     */
    protected abstract fun initVariable()

    /**
     * 初始化View的状态，挂载各种监听事件.
     */
    protected abstract fun initView()

    /**
     * 初始化加载页面数据.
     */
    protected abstract fun loadData()

    /**
     * 显示progressDialog

     * @param message 显示信息
     */
    protected fun showProgressDialog(message: String) {
        if (!activity.isDestroyed && !isHidden && progressDialog.isShowing) {
            progressDialog.dismiss()
        }
        progressDialog.setMessage(message)
        progressDialog.show()
    }

    /**
     * 显示progressDialog

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
        Utility.runOnUiThread(Runnable {
            if (progressDialog.isShowing) {
                progressDialog.dismiss()
            }
        }, 300)

    }
}
