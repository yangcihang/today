package com.hrsoft.today.widget

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.widget.ImageButton
import android.widget.RelativeLayout
import android.widget.TextView
import com.hrsoft.today.R
import kotlinx.android.synthetic.main.fragment_recommend.*

/**
 * @author YangCihang.
 * @since 17/11/18 16:10.
 * email yangcihang@hrsoft.net.
 */

class AddSubView : RelativeLayout {
    private lateinit var addImg: ImageButton
    private lateinit var subImg: ImageButton
    private lateinit var numTxt: TextView
    var miniNum = 2
    var maxNum = 20
    var pickNum = 2

    constructor(context: Context) : super(context)

    constructor(context: Context, attrs: AttributeSet) : super(context, attrs) {
        LayoutInflater.from(context).inflate(R.layout.view_selected_num, this, true)
        addImg = findViewById(R.id.img_add)
        subImg = findViewById(R.id.img_sub)
        numTxt = findViewById(R.id.txt_num)
        subImg.visibility = View.GONE
        addImg.setOnClickListener { textChange(++pickNum) }
        subImg.setOnClickListener { textChange(--pickNum) }
    }

    /**
     * 文字改变时回调
     */
    private fun textChange(number: Int) {
        numTxt.text = number.toString()
        when (number) {
            miniNum -> subImg.visibility = View.GONE
            maxNum -> addImg.visibility = View.GONE
            else -> {
                subImg.visibility = View.VISIBLE
                addImg.visibility = View.VISIBLE
            }
        }
    }
}
