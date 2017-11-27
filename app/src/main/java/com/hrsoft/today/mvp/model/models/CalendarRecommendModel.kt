package com.hrsoft.today.mvp.model.models

import java.io.Serializable

/**
 * @author YangCihang
 * @since  17/11/2.
 * email yangcihang@hrsoft.net
 */
//创建和修改推荐的model
class CalendarRecommendModel(var name: String? = "",
                             var items: List<String> = mutableListOf(),
                             var item: String? = "",
                             var pickCount: Int) : Serializable {
}