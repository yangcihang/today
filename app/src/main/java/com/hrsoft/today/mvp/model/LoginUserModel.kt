package com.hrsoft.today.mvp.model

import java.io.Serializable

/**
 * @author YangCihang.
 * @since 17/11/20 22:24.
 * email yangcihang@hrsoft.net.
 */
class LoginUserModel(var token: String? = null,
                     var name: String? = null,
                     var signature: String? = null,
                     var avatar: String? = null,
                     var sex: Int = -1) : Serializable