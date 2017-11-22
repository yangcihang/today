package com.hrsoft.today.mvp.model

import java.io.Serializable

/**
 * @author YangCihang
 * @since  17/11/1.
 * email yangcihang@hrsoft.net
 */
class CalendarModel : Serializable {
    var calendarPicture: String? = ""
    var calendarId: Int? = null
    var calendarName: String? = ""
    var good: List<CalendarStateModel>? = emptyList()
    var bad: List<CalendarStateModel>? = emptyList()
    var recommend: List<CalendarRecommendModel>? = emptyList()
    var order: Int = 0
}