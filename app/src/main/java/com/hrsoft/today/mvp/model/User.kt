package com.hrsoft.today.mvp.model

/**
 * @author YangCihang
 * @since  17/11/1.
 * email yangcihang@hrsoft.net
 */
object User {
    /**用户订阅的黄历*/
    var userCalendarList: List<CalendarModel>? = null
    var token: String? = ""
}