package com.hrsoft.today.mvp.model

import java.io.Serializable

/**
 * @author YangCihang
 * @since  17/11/6.
 * email yangcihang@hrsoft.net
 */
class NewCalendarModel : Serializable {
    var title: String? = ""
    var description: String? = ""
    var picture: String? = ""
    var isPublic: Int? = null
    var password: String? = ""
    var goodPick: Int? = null
    var badPick: Int? = null
}