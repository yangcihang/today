package com.hrsoft.today.mvp.model

import android.content.Context.MODE_PRIVATE
import android.content.SharedPreferences
import android.text.TextUtils
import com.hrsoft.today.App
import com.hrsoft.today.common.Config
import com.hrsoft.today.mvp.model.models.CalendarModel
import java.io.Serializable

/**
 * @author YangCihang
 * @since  17/11/1.
 * email yangcihang@hrsoft.net
 */
object User : Serializable {
    private val KEY_TOKEN = "KEY_TOKEN"
    private val KEY_NAME = "KEY_NAME"
    private val KEY_SEX = "KEY_SEX"
    private val KEY_AVATAR = "KEY_AVATAR"
    private val KEY_SIGNATURE = "KEY_SIGNATURE"
    /**用户订阅的黄历*/
    var userCalendarList: MutableList<CalendarModel> = mutableListOf()
    var token: String? = ""
    var name: String? = ""
    var signature: String? = ""
    var avatar: String? = ""
    var sex = -1

    fun saveUserInfo() {
        val sp: SharedPreferences = App.instance.getSharedPreferences(User.javaClass.name, MODE_PRIVATE)
        sp.edit()
                .putString(KEY_TOKEN, User.token)
                .putString(KEY_NAME, User.name)
                .putString(KEY_AVATAR, User.avatar)
                .putString(KEY_SIGNATURE, User.signature)
                .apply()
    }

    /**
     * 数据加载
     */
    fun loadUserInfo() {
        App.instance.getSharedPreferences(User.javaClass.name, MODE_PRIVATE).apply {
            User.token = getString(KEY_TOKEN, "")
            User.name = getString(KEY_NAME, "")
            User.avatar = getString(KEY_AVATAR, "")
            User.signature = getString(KEY_SIGNATURE, "")
        }
        App.instance.getCacheUtil().getSerializableObj(Config.KEY_CALENDAR)?.let {
            User.userCalendarList = it as MutableList<CalendarModel>
        }
    }

    /**
     * 是否登录
     */
    fun isLogin(): Boolean {
        return !TextUtils.isEmpty(token) || !TextUtils.isEmpty(name)
    }

    fun clear() {
        App.instance.getSharedPreferences(User.javaClass.name, MODE_PRIVATE).edit().clear().apply()
        userCalendarList = mutableListOf()
        token = ""
        name = ""
        signature = ""
        avatar = ""
    }
}