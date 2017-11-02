package com.hrsoft.today.mvp.model

import com.hrsoft.today.mvp.view.main.fragment.MainContentFragment

/**
 * @author YangCihang
 * @since  17/11/1.
 * email yangcihang@hrsoft.net
 */
object User {
    /**用户订阅的黄历*/
    var userCalendarList: MutableList<CalendarModel> = emptyList<CalendarModel>() as MutableList<CalendarModel>
}