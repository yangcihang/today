package com.hrsoft.today.mvp.model

import java.io.Serializable

/**
 * @author YangCihang
 * @since  17/11/5.
 * email yangcihang@hrsoft.net
 */
class SimpleCalendarModel : Serializable {
    var id: Int? = null
    var title: String? = ""
    var picture: String? = ""
    var subscribed: Int? = null
    var isSubscribed: Boolean = false
}