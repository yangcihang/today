package com.hrsoft.today.mvp.view.manage.fragment

import android.content.Context
import android.content.Intent
import android.text.TextUtils
import android.view.View
import android.widget.TextView
import com.bumptech.glide.Glide
import com.hrsoft.today.App
import com.hrsoft.today.R
import com.hrsoft.today.base.BaseFragment
import com.hrsoft.today.mvp.model.NewCalendarModel
import com.hrsoft.today.mvp.view.manage.activity.CreateCalendarActivity
import com.hrsoft.today.util.ImagePickerUtil
import com.hrsoft.today.util.ToastUtil
import com.lwkandroid.imagepicker.ImagePicker
import com.lwkandroid.imagepicker.data.ImageBean
import com.lwkandroid.imagepicker.data.ImagePickType
import kotlinx.android.synthetic.main.fragment_description.*


/**
 * @author YangCihang
 * @since  17/11/6.
 * email yangcihang@hrsoft.net
 */
class CalendarDescriptionFragment : BaseFragment() {
    var netPicturePath = ""
    private var mActivity: CreateCalendarActivity? = null
    /** 好坏的范围：2~5个*/
    private var goodPick = 2
    private var badPick = 2
    private var miniNum = 2
    private var maxNum = 5
    private var picturePath = ""
    private var REQUEST_CODE = 1 shl 5
    lateinit var onRequestQiNiuTokenListener: ((String) -> Unit)

    override fun getLayoutId(): Int {
        return R.layout.fragment_description
    }

    override fun initVariable() {
    }

    override fun initView() {
        img_good_add.setOnClickListener { goodTextChange(txt_good_pick, ++goodPick) }
        img_good_sub.setOnClickListener { goodTextChange(txt_good_pick, --goodPick) }
        img_bad_add.setOnClickListener { badTextChange(txt_bad_pick, ++badPick) }
        img_bad_sub.setOnClickListener { badTextChange(txt_bad_pick, --badPick) }
        img_create_calendar.setOnClickListener {
            ImagePicker()
                    .pickType(ImagePickType.SINGLE)
                    .needCamera(true)
                    .cachePath(App.instance.cachePath)
                    .displayer(ImagePickerUtil())
                    .doCrop(1, 1, 480, 480)
                    .start(this@CalendarDescriptionFragment, REQUEST_CODE)
        }
    }

    override fun loadData() {
    }

    override fun onAttach(context: Context?) {
        super.onAttach(context)
        mActivity = context as CreateCalendarActivity?
    }

    private fun goodTextChange(target: TextView, number: Int) {
        target.text = number.toString()
        when (number) {
            miniNum -> img_good_sub.visibility = View.GONE
            maxNum -> img_good_add.visibility = View.GONE
            else -> {
                img_good_sub.visibility = View.VISIBLE
                img_good_add.visibility = View.VISIBLE
            }
        }
    }

    private fun badTextChange(target: TextView, number: Int) {
        target.text = number.toString()
        when (number) {
            miniNum -> img_bad_sub.visibility = View.GONE
            maxNum -> img_bad_add.visibility = View.GONE
            else -> {
                img_bad_sub.visibility = View.VISIBLE
                img_bad_add.visibility = View.VISIBLE
            }
        }
    }

    /**
     * 获取日历的描述信息
     */
    fun getCalendarDescription(): NewCalendarModel {
        return NewCalendarModel(title = edit_create_calendar_title.text.toString().trim(),
                description = edit_create_calendar_description.text.toString().trim(),
                badPick = badPick,
                goodPick = goodPick,
                picture = netPicturePath
        )
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == REQUEST_CODE && data != null) {
            val resultList = data.getParcelableArrayListExtra<ImageBean>(ImagePicker.INTENT_RESULT_DATA)
            picturePath = resultList[0].imagePath
            Glide.with(this).load(picturePath).placeholder(R.mipmap.ic_launcher).into(img_create_calendar)
            if (!TextUtils.isEmpty(picturePath)) {
                onRequestQiNiuTokenListener.invoke(picturePath)
            } else ToastUtil.showToast(R.string.toast_image_load_error)
        }
    }

}