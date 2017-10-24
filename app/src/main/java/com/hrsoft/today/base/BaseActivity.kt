package com.hrsoft.today.base

import android.content.pm.ActivityInfo
import android.os.Bundle
import android.support.v7.app.AppCompatActivity

/**
 * @author YangCihang
 * @since  17/9/23.
 * email yangcihang@hrsoft.net
 */
abstract class BaseActivity : AppCompatActivity() {
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
}