package com.hrsoft.today.base

import android.os.Bundle
import android.support.annotation.Nullable
import android.support.v7.widget.Toolbar
import android.view.MenuItem
import android.view.View
import android.widget.FrameLayout
import android.widget.RelativeLayout
import com.hrsoft.today.R

/**
 * @author YangCihang
 * @since  17/9/23.
 * email yangcihang@hrsoft.net
 */
abstract class ToolbarActivity : BaseActivity() {
    protected lateinit var toolbar: Toolbar
    override fun onCreate(@Nullable savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(toolbarView)
        init()
    }

    /**
     * 返回带有toolbar的总布局
     */
    private val toolbarView: View
        get() {
            val inflater = layoutInflater
            val viewRoot = inflater.inflate(R.layout.view_toolbar_base, null) as RelativeLayout
            val viewContainer = viewRoot.findViewById<View>(R.id.view_container) as FrameLayout
            viewContainer.addView(inflater.inflate(getLayoutId(), null))
            initToolbar(viewRoot)
            return viewRoot
        }

    /**
     * 初始化设置toolbar.

     * @param root 页面rootView
     */
    private fun initToolbar(root: View) {
        toolbar = root.findViewById<View>(R.id.toolbar) as Toolbar
        toolbar.title = ""
        setSupportActionBar(toolbar)
        val actionBar = supportActionBar
        if (actionBar != null) {
            actionBar.setHomeButtonEnabled(true)
            actionBar.setDisplayHomeAsUpEnabled(true)
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> {
                onBackBtnOnclick()
                return true
            }
            else -> return super.onOptionsItemSelected(item)
        }
    }

    /**
     * 设置activity 页面标题

     * @param charSequence 页面标题
     */
    protected fun setActivityTitle(charSequence: CharSequence) {
        toolbar.title = charSequence
        toolbar.setTitleTextColor(resources.getColor(R.color.white))
    }

    /**
     * Toolbar返回按钮的监听事件
     */
    protected fun onBackBtnOnclick() {
        this.finish()
    }
}
