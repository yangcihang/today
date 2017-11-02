package com.hrsoft.today.util

import com.hrsoft.today.common.Config
import java.util.regex.Pattern

/**
 * @author YangCihang
 * @since  17/11/2.
 * email yangcihang@hrsoft.net
 */

object RegexUtil {
    // 手机号的正则,11位手机号
    private val REGEX_MOBILE = "[1][3,4,5,7,8][0-9]{9}$"

    //手机号正则
    fun checkMobile(phone: String): Boolean {
        return Pattern.matches(REGEX_MOBILE, phone)
    }

    //验证邮箱
    fun checkEmail(email: String): Boolean {
        return Pattern.matches(Config.EMAIL_REGEX, email)
    }
}

