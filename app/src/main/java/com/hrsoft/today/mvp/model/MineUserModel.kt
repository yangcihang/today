package com.hrsoft.today.mvp.model

import java.io.Serializable

/**
 * @author YangCihang.
 * @since 17/11/23 21:15.
 * email yangcihang@hrsoft.net.
 */
class MineUserModel(var avatar: String = "",
                    var signature: String = "",
                    var oldPassword: String = "",
                    var confirmPassword: String = "",
                    var newPassword: String = "") : Serializable