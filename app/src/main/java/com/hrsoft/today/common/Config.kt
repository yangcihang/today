package com.hrsoft.today.common

/**
 * @author YangCihang
 * @since  17/9/24.
 * email yangcihang@hrsoft.net
 */
object Config {
    val MOBILE_REGEX = "^(13[0-9]|14[5|7]|15[0|1|2|3|5|6|7|8|9]|18[0|1|2|3|5|6|7|8|9])\\d{8}$"
    val EMAIL_REGEX = "^\\w+([-+.]\\w+)*@\\w+([-.]\\w+)*\\.\\w+([-.]\\w+)*$"
    val BASE_URL = "http://today.marklux.cn/"
    val APP_SERVER_CONNECT_TIME_OUT: Long = 15

    val KEY_CALENDAR = "calendar"
    val KEY_SQUARE_CALENDAR = "square_calendar"
}