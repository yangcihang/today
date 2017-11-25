package com.hrsoft.today.common

/**
 * @author YangCihang
 * @since  17/9/24.
 * email yangcihang@hrsoft.net
 */
object Config {
    val MOBILE_REGEX = "^(13[0-9]|14[5|7]|15[0|1|2|3|5|6|7|8|9]|18[0|1|2|3|5|6|7|8|9])\\d{8}$"
    val EMAIL_REGEX = "^\\w+([-+.]\\w+)*@\\w+([-.]\\w+)*\\.\\w+([-.]\\w+)*$"
    val BASE_URL = "http://tdapi.marklux.cn/"
    val APP_SERVER_CONNECT_TIME_OUT: Long = 15
    val MINI_CREATE_RECOMMEND_NUM = 2

    val KEY_CALENDAR = "calendar"
    val KEY_START_TYPE = "startType"
    val KEY_SQUARE_CALENDAR = "squareCalendar"
    val KEY_USER_INFO = "userInfo"
    val KEY_CALENDAR_DETAIL = "calendarDetail"
}