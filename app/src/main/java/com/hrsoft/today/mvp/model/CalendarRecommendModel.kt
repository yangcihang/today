package com.hrsoft.today.mvp.model

import java.io.Serializable

/**
 * @author YangCihang
 * @since  17/11/2.
 * email yangcihang@hrsoft.net
 */
class CalendarRecommendModel(var name: String? = "",
                             var items: List<String> = mutableListOf(),
                             var item: String? = "",
                             var pickCount: Int) : Serializable {
    fun setItem() {
        for (i in items) {
            item = item + i + " "
        }
    }
}