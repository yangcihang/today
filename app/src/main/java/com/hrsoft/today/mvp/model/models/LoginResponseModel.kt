package com.hrsoft.today.mvp.model.models

import java.io.Serializable

/**
 * @author YangCihang.
 * @since 17/11/17 22:56.
 * email yangcihang@hrsoft.net.
 */
class LoginResponseModel : Serializable {
    inner class User {
        var name: String? = ""
        var signature: String? = ""
        var avatar: String? = ""
        var sex = -1
    }

    lateinit var token: String
    lateinit var user: User
}