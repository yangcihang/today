package com.hrsoft.today.base

import android.os.Bundle

/**
 * @author YangCihang
 * @since  17/9/23.
 * email yangcihang@hrsoft.net
 */
abstract class NoBarActivity : BaseActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(getLayoutId())
        init()
    }
}