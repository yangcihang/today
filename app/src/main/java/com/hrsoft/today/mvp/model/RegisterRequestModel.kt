package com.hrsoft.today.mvp.model

import java.io.Serializable

/**
 * @author YangCihang.
 * @since 17/11/17 21:51.
 * email yangcihang@hrsoft.net.
 */
class RegisterRequestModel(var name: String = ""
                           , var password: String = ""
                           , var signature: String = ""
                           , var confirmPassword: String = ""
                           , var sex: Int = -1) : Serializable