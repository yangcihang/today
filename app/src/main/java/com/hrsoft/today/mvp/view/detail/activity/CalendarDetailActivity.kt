package com.hrsoft.today.mvp.view.detail.activity

import android.content.Context
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BlurMaskFilter
import android.os.Build
import android.os.WorkSource
import android.renderscript.Allocation
import android.renderscript.Element
import android.renderscript.RenderScript
import android.renderscript.ScriptIntrinsicBlur
import android.support.annotation.RequiresApi
import android.support.v7.graphics.Palette
import com.bumptech.glide.Glide
import com.hrsoft.today.R
import com.hrsoft.today.base.NoBarActivity
import com.hrsoft.today.common.Config
import com.hrsoft.today.mvp.contract.DetailContract
import com.hrsoft.today.mvp.model.CalendarDetailModel
import com.hrsoft.today.mvp.model.SquareCalendarModel
import com.hrsoft.today.mvp.presenter.DetailActivityPresenter
import com.hrsoft.today.mvp.view.detail.adapter.DetailAdapter
import com.hrsoft.today.mvp.view.detail.fragment.CommentFragment
import com.hrsoft.today.util.ToastUtil
import kotlinx.android.synthetic.main.activity_calendar_detail.*

/**
 * @author YangCihang
 * @since  17/11/5.
 * email yangcihang@hrsoft.net
 */
class CalendarDetailActivity : NoBarActivity(), DetailContract.View, CommentFragment.GetCalendarIdListener {


    private var calendarId = 0

    companion object {
        fun start(context: Context, model: SquareCalendarModel) {
            context.startActivity(Intent(context, CalendarDetailActivity::class.java).apply {
                putExtra(Config.KEY_SQUARE_CALENDAR, model)
            })
        }
    }

    override var mPresenter: DetailContract.Presenter? = DetailActivityPresenter(this)

    override fun initVariable() {
    }

    override fun initView() {

        app_bar_detail.addOnOffsetChangedListener { appBarLayout, verticalOffset ->
            when {
                verticalOffset == 0 -> {
                    toolbar.setTitleTextColor(this@CalendarDetailActivity.resources.getColor(R.color.white))
                    tabs.setTabTextColors(resources.getColor(R.color.view_divide), resources.getColor(R
                            .color
                            .white))
                }
                Math.abs(verticalOffset) >= appBarLayout.totalScrollRange -> {
                    toolbar.setTitleTextColor(this@CalendarDetailActivity.resources.getColor(R.color.black))
                    tabs.setTabTextColors(resources.getColor(R.color.text_primary), resources.getColor(R.color
                            .black))
                }
                else -> {
                    toolbar.setTitleTextColor(this@CalendarDetailActivity.resources.getColor(R.color.accent))
                    tabs.setTabTextColors(resources.getColor(R.color.view_divide), resources.getColor(R
                            .color
                            .accent))
                }
            }
        }

        val adapter = DetailAdapter(supportFragmentManager)
        vp_calendar_detail.adapter = adapter
        tabs.setupWithViewPager(vp_calendar_detail)


    }

    override fun loadData() {
//        mPresenter?.getCalendarInfo(calendarId)
    }

    override fun getLayoutId(): Int {
        return R.layout.activity_calendar_detail
    }

    override fun spreadCalendarId(): Int {
        return calendarId
    }

    override fun onDestroy() {
        super.onDestroy()
        mPresenter?.onDetach()
    }

    override fun onDetailLoaded(mData: CalendarDetailModel) {
        txt_calendar_title.text = mData.title
        txt_user_name.text = mData.creatorName
        Glide.with(this).load(mData.picture).into(img_user_avatar)
        Glide.with(this).load(mData.creatorAvatar).into(calendar_avatar)
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) {
            setBackground(Glide.with(this@CalendarDetailActivity).load(mData.creatorAvatar).asBitmap().centerCrop().into
            (500,500).get(),23f)
        }
    }

    override fun onDetailLoadFailed() {
        ToastUtil.showToast("信息加载失败，请稍后再试")

    }

    @RequiresApi(Build.VERSION_CODES.JELLY_BEAN_MR1)
    private fun setBackground(source: Bitmap, radius: Float) {
        var inputBmp = source
        var renderScript = RenderScript.create(this)
        val input = Allocation.createFromBitmap(renderScript,inputBmp)
        val output = Allocation.createTyped(renderScript,input.type)
        var scriptIntrinsicBlur = ScriptIntrinsicBlur.create(renderScript, Element.U8_4(renderScript))
        scriptIntrinsicBlur.setInput(input)
        scriptIntrinsicBlur.setRadius(radius)
        scriptIntrinsicBlur.forEach(output)
        output.copyTo(inputBmp)
        renderScript.destroy()
        Glide.with(this).load(inputBmp).into(img_calendar_bg)
    }

}

