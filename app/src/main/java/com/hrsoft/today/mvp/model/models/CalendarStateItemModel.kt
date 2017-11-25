package com.hrsoft.today.mvp.model.models

import java.io.Serializable

/**
 * @author YangCihang
 * @since  17/11/6.
 * email yangcihang@hrsoft.net
 */
//修改和创建状态的model
class CalendarStateItemModel(var name: String? = "",
                             var good: String? = "",
                             var bad: String? = "",
                             var weekendOnly: Int? = null) : Serializable