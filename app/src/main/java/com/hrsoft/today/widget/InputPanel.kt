package com.hrsoft.today.widget

import android.annotation.SuppressLint
import android.content.Context
import android.text.InputType
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.EditText
import android.widget.FrameLayout
import android.widget.RelativeLayout
import android.widget.TextView
import com.hrsoft.today.R
import com.hrsoft.today.util.Utility

/**
 * @author YangCihang.
 * @since 2017/11/21 0021.
 * Email yangcihang@hrsoft.net
 */
class InputPanel : RelativeLayout {
    private lateinit var inputPanelEdit: EditText
    private lateinit var sendBtn: TextView
    private var isKeyboardShowed: Boolean = false
    private var delayTime: Long = 300
    var onSendListener: ((String) -> Unit)? = null

    constructor(context: Context) : super(context) {
        LayoutInflater.from(context).inflate(R.layout.view_input_panel_bar, this, true)
        init()
    }


    constructor(context: Context, attrs: AttributeSet) : super(context, attrs) {
        LayoutInflater.from(context).inflate(R.layout.view_input_panel_bar, this, true)
        init()
    }

    constructor(context: Context, attrs: AttributeSet, defStyleAttr: Int) : super(context, attrs, defStyleAttr) {
        LayoutInflater.from(context).inflate(R.layout.view_input_panel_bar, this, true)
        init()
    }

    private fun init() {
        inputPanelEdit = findViewById(R.id.edit_input_panel_toolbar_edit)
        sendBtn = findViewById(R.id.fl_send_or_input_plus)
        sendBtn.setOnClickListener {
            onSendListener?.invoke(inputPanelEdit.text.toString().trim())
        }
        initTextEdit()
    }

    /**
     * 初始化文字输入框
     */
    @SuppressLint("ClickableViewAccessibility")
    private fun initTextEdit() {
        inputPanelEdit.inputType = InputType.TYPE_CLASS_TEXT or InputType.TYPE_TEXT_FLAG_MULTI_LINE
        inputPanelEdit.setOnTouchListener { v, event ->
            if (event.action == MotionEvent.ACTION_DOWN) {
                switchToTextLayout(true)
            }
            false
        }
        hideInputMethod()
    }


    /**
     * 切换到文字输入布局
     *
     * @param isShowInput 是否打开键盘
     */
    private fun switchToTextLayout(isShowInput: Boolean) {
        if (isShowInput) {
            Utility.runOnUiThread(Runnable { showInputMethod() }, delayTime)
        } else {
            Utility.runOnUiThread(Runnable {
                hideInputMethod()
            }, delayTime)
        }
    }

    /**
     * 显示输入面板
     */
    private fun showInputMethod() {
        inputPanelEdit.requestFocus()
        // 如果已经显示,则继续操作时不需要把光标定位到最后
        if (!isKeyboardShowed) {
            inputPanelEdit.setSelection(inputPanelEdit.text.length)
            isKeyboardShowed = true
        }
        val imm = context!!.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.showSoftInput(inputPanelEdit, InputMethodManager.SHOW_FORCED)

    }


    /**
     * 隐藏软键盘
     */
    private fun hideInputMethod() {
        if (isKeyboardShowed) {
            isKeyboardShowed = false
            val imm = context!!.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            imm.hideSoftInputFromWindow(inputPanelEdit.windowToken, 0)
            inputPanelEdit.clearFocus()
        }
    }

}