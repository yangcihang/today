package com.hrsoft.today.network

import com.hrsoft.today.common.Config
import com.hrsoft.today.mvp.model.*
import com.hrsoft.today.mvp.model.models.*
import okhttp3.OkHttpClient
import okhttp3.Response
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.*
import java.util.concurrent.TimeUnit


/**
 * @author YangCihang
 * @since  17/9/25.
 * email yangcihang@hrsoft.net
 */
interface NetWork {
    companion object {
        fun getService(): NetWork {
            fun getRetrofit(): Retrofit {
                val httpClient = OkHttpClient.Builder()
                        .addInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
                        .addNetworkInterceptor { chain ->
                            val request = chain?.request()?.newBuilder()?.addHeader("token", User.token)?.build()
                            val response: Response = chain?.proceed(request)!!
                            response
                        }
                        .connectTimeout(Config.APP_SERVER_CONNECT_TIME_OUT, TimeUnit.SECONDS)
                        .build()

                fun provideRetrofit(baseUrl: String, client: OkHttpClient): Retrofit =
                        Retrofit.Builder()
                                .client(client)
                                .baseUrl(baseUrl)
                                .addConverterFactory(GsonConverterFactory.create())
                                .build()


                return provideRetrofit(Config.BASE_URL, httpClient)
            }
            return getRetrofit().create(NetWork::class.java)
        }
    }

    @GET("calendar/subscribed")
    fun requestCalendarList(): Call<RspModel<List<CalendarModel>>>

    @GET("piazza/most-subscribed")
    fun requestSquareRecommendCalendarList(): Call<RspModel<List<SimpleCalendarModel>>>

    @GET("piazza/all")
    fun requestSquareAllCalendarList(@Query("page") page: Int): Call<RspModel<List<SimpleCalendarModel>>>

    @GET("piazza/search")
    fun requestSearchModelList(@Query("keyword") key: String, @Query("page") page: Int): Call<RspModel<List<SimpleCalendarModel>>>

    @GET("calendar/{calendarId}/comment")
    fun requestCalendarCommentList(@Path("calendarId") calendarId: Int, @Query("page") page: Int): Call<RspModel<List<CommentModel>>>

    /**
     * 获取黄历详情信息
     */
    @GET("calendar/{calendarId}/detail")
    fun requestCalendarDetail(@Path("calendarId") calendarId: Int): Call<RspModel<CalendarDetailModel>>

    @POST("custom/new")
    fun createNewCalendar(@Body model: NewCalendarModel): Call<RspModel<Long>>

    @POST("custom/{calendarId}/activities")
    fun updateCalendarStates(@Path("calendarId") id: Long, @Body model: List<CalendarStateItemModel>): Call<RspModel<Long>>

    @POST("custom/{Id}/items")
    fun updateCalendarRecommend(@Path("Id") id: Int, @Body modelList: List<CalendarRecommendModel>): Call<RspModel<Long>>

    @GET("custom/created")
    fun getCreatedCalendar(): Call<RspModel<List<SimpleCalendarModel>>>

    /**
     * 删除用户创建的黄历
     */
    @DELETE("custom/{calendarId}")
    fun requestDeleteCreatedCalendar(@Path("calendarId") id: Int): Call<RspModel<Long>>

    /**
     * 七牛Token
     */
    @GET("custom/upload/token")
    fun getToken(): Call<RspModel<String>>

    /**
     * 注册
     */
    @POST("user/register")
    fun register(@Body requestModel: RegisterRequestModel): Call<RspModel<Long>>

    /**
     * 登录
     */
    @POST("user/login")
    fun login(@Body loginRequestModel: LoginRequestModel): Call<RspModel<LoginResponseModel>>

    /**
     * 订阅
     */
    @GET("calendar/{calendarId}/subscribe")
    fun subscribeCalendar(@Path("calendarId") id: Int): Call<RspModel<Unit>>

    /**
     * 取消订阅
     */
    @GET("calendar/{calendarId}/unsubscribe")
    fun unsubscribeCalendar(@Path("calendarId") id: Int): Call<RspModel<Unit>>

    /**
     * 评论
     */
    @POST("calendar/{calendarId}/comment")
    fun comment(@Path("calendarId") id: Int, @Body comment: CommentModel): Call<RspModel<Unit>>

    /**
     * 排序
     */
    @PUT("calendar/subscribe")
    fun orderCalendar(@Body calendarList: List<CalendarModel>): Call<RspModel<Unit>>

    /**
     * 更改用户图像
     */
    @PUT("user/avatar")
    fun updateAvatar(@Body model: MineUserModel): Call<RspModel<String>>

    /**
     * 修改用户签名
     */
    @PUT("user/signature")
    fun updateSignature(@Body model: MineUserModel): Call<RspModel<Unit>>

    /**
     * 修改用户密码
     */
    @PUT("user/password")
    fun updatePsw(@Body model: MineUserModel): Call<RspModel<Unit>>

    /**
     * 更新黄历详情
     */
    @PUT("custom/{calendarId}")
    fun updateCalendarDescription(@Path("calendarId") id: Int, @Body model: NewCalendarModel): Call<RspModel<Unit>>

    /**
     * 获取黄历state项
     */
    @GET("custom/{calendarId}/activities")
    fun getCalendarStateInfo(@Path("calendarId") id: Int): Call<RspModel<List<CalendarStateItemModel>>>

    /**
     * 获取黄历recommend项目
     */
    @GET("custom/{calendarId}/items")
    fun getCalendarRecommendInfo(@Path("calendarId") id: Int): Call<RspModel<List<CalendarRecommendModel>>>

}
