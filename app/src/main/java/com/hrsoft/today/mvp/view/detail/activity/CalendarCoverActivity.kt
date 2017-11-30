package com.hrsoft.today.mvp.view.detail.activity

import android.content.Intent
import android.view.MotionEvent
import com.bumptech.glide.Glide
import com.hrsoft.today.R
import com.hrsoft.today.base.BaseActivity
import com.hrsoft.today.base.NoBarActivity
import com.hrsoft.today.common.Config
import com.hrsoft.today.mvp.model.models.CalendarDetailModel
import jp.wasabeef.glide.transformations.BlurTransformation
import kotlinx.android.synthetic.main.activity_calendar_cover.*

/**
 * @author YangCihang.
 * @since 17/11/27 15:40.
 * email yangcihang@hrsoft.net.
 */
class CalendarCoverActivity : NoBarActivity() {
    private lateinit var calendarModel: CalendarDetailModel

    companion object {
        fun start(context: BaseActivity, model: CalendarDetailModel) {
            context.startActivity(Intent(context, CalendarCoverActivity::class.java).apply {
                putExtra(Config.KEY_SQUARE_CALENDAR, model)
            })
        }
    }

    override fun initVariable() {
        calendarModel = intent.getSerializableExtra(Config.KEY_SQUARE_CALENDAR) as CalendarDetailModel
    }

    override fun initView() {
        Glide.with(this).load(calendarModel.picture)
                .bitmapTransform(BlurTransformation(this, 20, 10))
                .dontAnimate()
                .into(img_cover_bg)
        Glide.with(this).load(calendarModel.picture).into(img_cover_avatar)
        txt_cover_title.text = calendarModel.title
        txt_cover_description.text = calendarModel.description

    }

    override fun loadData() {
    }

    override fun getLayoutId(): Int = R.layout.activity_calendar_cover

    override fun onTouchEvent(event: MotionEvent?): Boolean {
        if (event!!.action == MotionEvent.ACTION_DOWN) {
            this.finish()
        }
        return super.onTouchEvent(event)
    }
}