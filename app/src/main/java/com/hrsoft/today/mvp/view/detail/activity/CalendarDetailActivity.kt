package com.hrsoft.today.mvp.view.detail.activity

import android.content.Context
import android.content.Intent
import com.bumptech.glide.Glide
import com.hrsoft.today.R
import com.hrsoft.today.base.NoBarActivity
import com.hrsoft.today.common.Config
import com.hrsoft.today.mvp.contract.DetailContract
import com.hrsoft.today.mvp.model.models.CalendarDetailModel
import com.hrsoft.today.mvp.model.models.SimpleCalendarModel
import com.hrsoft.today.mvp.presenter.DetailActivityPresenter
import com.hrsoft.today.mvp.view.detail.adapter.DetailPagerAdapter
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
class CalendarDetailActivity : NoBarActivity(), DetailContract.View {

    private lateinit var calendarModel: SimpleCalendarModel
    private var isSubscribed: Boolean = false
    override var mPresenter: DetailContract.Presenter? = DetailActivityPresenter(this)

    companion object {
        fun start(context: Context, model: SimpleCalendarModel) {
            context.startActivity(Intent(context, CalendarDetailActivity::class.java).apply {
                putExtra(Config.KEY_SQUARE_CALENDAR, model)
            })
        }
    }


    override fun initVariable() {
        calendarModel = intent.getSerializableExtra(Config.KEY_SQUARE_CALENDAR) as SimpleCalendarModel
    }

    override fun initView() {
        initToolbar()
        // 渲染页面数据
        isSubscribed = calendarModel.isSubscribed
        if (isSubscribed) {
            txt_good_sum.setTextColor(resources.getColor(R.color.accent))
        }
        btn_subscribe.isSelected = isSubscribed
        img_subscribe.setSelected(isSubscribed)
        txt_calendar_title.text = calendarModel.title
        Glide.with(this).load(calendarModel.picture)
                .bitmapTransform(BlurTransformation(this, 14, 3))
                .dontAnimate()
                .error(R.mipmap.ic_launcher)
                .into(img_calendar_bg)
        toolbar.setNavigationOnClickListener { finish() }
        btn_subscribe.setOnClickListener {
            if (!isSubscribed) {
                mPresenter?.subscribeCalendar(calendarModel.id!!)
            } else {
                mPresenter?.unSubscribeCalendar(calendarModel.id!!)
            }
        }
    }


    override fun loadData() {
        mPresenter?.getCalendarInfo(calendarModel.id!!)
    }

    override fun getLayoutId(): Int {
        return R.layout.activity_calendar_detail
    }

    /**
     * 初始化ViewPager
     */
    private fun initViewPager(mData: CalendarDetailModel) {
        tabs.setTabTextColors(resources.getColor(R.color.text_ternary), resources.getColor(R.color.black))
        vp_calendar_detail.adapter = DetailPagerAdapter(supportFragmentManager, mData)
        tabs.setupWithViewPager(vp_calendar_detail)
    }

    /**
     * 初始化toolbar
     */
    private fun initToolbar() {
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
        initViewPager(mData)
    }


    override fun onDetailLoadFailed() {
        ToastUtil.showToast("信息加载失败，请稍后再试")
    }

    override fun onSubscribeSuccess() {
        Utility.runOnUiThread(Runnable {
            ToastUtil.showToast(R.string.toast_subscribe_success)
            isSubscribed = true
            img_subscribe.isSelected = true
            btn_subscribe.isSelected = true
            txt_good_sum.setTextColor(resources.getColor(R.color.accent))
        })
    }

    override fun onSubscribeFailed() {
        Utility.runOnUiThread(Runnable {
            ToastUtil.showToast(R.string.toast_subscribe_failed)
            isSubscribed = false
            img_subscribe.isSelected = false
        })
    }

    override fun onUnsubscribeSuccess() {
        Utility.runOnUiThread(Runnable {
            ToastUtil.showToast(R.string.toast_unsubscribe_success)
            isSubscribed = false
            img_subscribe.isSelected = false
            btn_subscribe.isSelected = false
            txt_good_sum.setTextColor(resources.getColor(R.color.white))
        })
    }

    override fun onUnsubscribeFailed() {
        Utility.runOnUiThread(Runnable {
            ToastUtil.showToast(R.string.toast_unsubscribe_failed)
            isSubscribed = true
            img_subscribe.isSelected = true
        })
    }

    override fun onDestroy() {
        super.onDestroy()
        mPresenter?.onDetach()
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

