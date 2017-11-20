package com.hrsoft.today.mvp.model

import java.io.Serializable

/**
 * @author YangCihang.
 * @since 17/11/17 21:50.
 * email yangcihang@hrsoft.net.
 */
class LoginRequestModel(var name: String = ""
                        , var password: String = ""
                        , var client: Int = 1) : Serializable