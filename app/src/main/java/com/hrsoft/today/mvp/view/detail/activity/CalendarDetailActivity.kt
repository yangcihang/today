package com.hrsoft.today.mvp.view.detail.activity

import android.content.Context
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.drawable.BitmapDrawable
import android.os.Build
import android.renderscript.Allocation
import android.renderscript.Element
import android.renderscript.RenderScript
import android.renderscript.ScriptIntrinsicBlur
import com.bumptech.glide.Glide
import com.hrsoft.today.R
import com.hrsoft.today.base.NoBarActivity
import com.hrsoft.today.common.Config
import com.hrsoft.today.mvp.contract.DetailContract
import com.hrsoft.today.mvp.model.CalendarDetailModel
import com.hrsoft.today.mvp.model.SimpleCalendarModel
import com.hrsoft.today.mvp.presenter.DetailActivityPresenter
import com.hrsoft.today.mvp.view.detail.adapter.DetailPagerAdapter
import com.hrsoft.today.mvp.view.detail.fragment.CommentFragment
import com.hrsoft.today.util.ToastUtil
import com.hrsoft.today.util.Utility
import com.hrsoft.today.widget.CropCircleTransformation
import jp.wasabeef.glide.transformations.BlurTransformation
import kotlinx.android.synthetic.main.activity_calendar_detail.*

/**
 * @author YangCihang
 * @since  17/11/5.
 * email yangcihang@hrsoft.net
 */
class CalendarDetailActivity : NoBarActivity(), DetailContract.View, CommentFragment.GetCalendarIdListener {


    private lateinit var calendarModel: SimpleCalendarModel

    companion object {
        fun start(context: Context, model: SimpleCalendarModel) {
            context.startActivity(Intent(context, CalendarDetailActivity::class.java).apply {
                putExtra(Config.KEY_SQUARE_CALENDAR, model)
            })
        }
    }

    override var mPresenter: DetailContract.Presenter? = DetailActivityPresenter(this)

    override fun initVariable() {
        calendarModel = intent.getSerializableExtra(Config.KEY_SQUARE_CALENDAR) as SimpleCalendarModel

    }

    override fun initView() {
        // 获取toolbar的折叠凳状态，以修改文字颜色等
        app_bar_detail.addOnOffsetChangedListener { appBarLayout, verticalOffset ->
            when {
            // 折叠，真实值=0，加范围以防止误触
                (verticalOffset in 0..20) -> {
                    toolbar.setTitleTextColor(this@CalendarDetailActivity.resources.getColor(R.color.white))
                    toolbar.navigationIcon = this@CalendarDetailActivity.resources.getDrawable(R.drawable.ic_back_white)
                }
            // 展开，真实值为appBarLayout.totalScrollRange
                Math.abs(verticalOffset) >= appBarLayout.totalScrollRange - 20 -> {
                    toolbar.setTitleTextColor(this@CalendarDetailActivity.resources.getColor(R.color.black))
                    toolbar.navigationIcon = this@CalendarDetailActivity.resources.getDrawable(R.drawable.ic_back_black)
                }
            }
        }
        tabs.setTabTextColors(resources.getColor(R.color.text_ternary), resources.getColor(R.color.black))
        vp_calendar_detail.adapter = DetailPagerAdapter(supportFragmentManager)
        tabs.setupWithViewPager(vp_calendar_detail)

        // 渲染页面数据
        txt_calendar_title.text = calendarModel.title
        Glide.with(this).load(calendarModel.picture)
                .bitmapTransform(BlurTransformation(this, 14, 3))
                .dontAnimate()
                .error(R.mipmap.ic_launcher)
                .into(img_calendar_bg)
        toolbar.setNavigationOnClickListener { finish() }
    }

    override fun loadData() {
        mPresenter?.getCalendarInfo(calendarModel.id!!)
    }

    override fun getLayoutId(): Int {
        return R.layout.activity_calendar_detail
    }

    /**
     *     将ID传给fragment，以便获取评论
     */
    override fun spreadCalendarId(): Int {
        return calendarModel.id!!
    }

    override fun onDestroy() {
        super.onDestroy()
        mPresenter?.onDetach()
    }

    /**
     * 成功获取详情后的回调
     */
    override fun onDetailLoaded(mData: CalendarDetailModel) {
        txt_calendar_title.text = mData.title
        txt_user_name.text = mData.creatorName
        txt_good_sum.text = mData.goodPick.toString()
        Glide.with(this).load(mData.creatorAvatar).bitmapTransform(CropCircleTransformation(this)).into(img_user_avatar)
        Glide.with(this).load(mData.picture).into(calendar_avatar)
    }

    override fun onDetailLoadFailed() {
        ToastUtil.showToast("信息加载失败，请稍后再试")
    }

//    /**
//     * 设置高斯模糊背景图
//     */
//    private fun setBackground(source: Bitmap, radius: Float) {
//        val inputBmp = source
//        val renderScript = RenderScript.create(this)
//        val input = Allocation.createFromBitmap(renderScript, inputBmp)
//        val output = Allocation.createTyped(renderScript, input.type)
//        val scriptIntrinsicBlur = ScriptIntrinsicBlur.create(renderScript, Element.U8_4(renderScript))
//        scriptIntrinsicBlur.setInput(input)
//        scriptIntrinsicBlur.setRadius(radius)
//        scriptIntrinsicBlur.forEach(output)
//        output.copyTo(inputBmp)
//        renderScript.destroy()
//        Utility.runOnUiThread(Runnable {
//            img_calendar_bg.setImageDrawable(BitmapDrawable(resources, inputBmp))
//        })
//
//    }
}

