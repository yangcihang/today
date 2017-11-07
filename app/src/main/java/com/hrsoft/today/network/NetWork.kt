package com.hrsoft.today.network

import com.hrsoft.today.common.Config
import com.hrsoft.today.mvp.model.*
import okhttp3.OkHttpClient
import okhttp3.Response
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.*
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query
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
    fun requestCalendarCommentList(@Path("calendarId")calendarId:Int): Call<RspModel<List<CommentModel>>>

    @GET("calendar/{calendarId}/detail")
    fun requestCalendarDetail(@Path("calendarId")calendarId:Int): Call<RspModel<CalendarDetailModel>>

    @POST("custom/new")
    fun createNewCalendar(@Body model: NewCalendarModel): Call<RspModel<Long>>

    @POST("custom/{calendarId}/activities")
    fun createCalendarStates(@Path("calendarId") id: Int, @Body model: List<CalendarStateItemModel>): Call<RspModel<Long>>

    @POST("custom/{Id}/items")
    fun createCalendarRecommend(@Path("Id") id: Int, @Body modelList: List<NewCalendarRecommendModel>): Call<RspModel<Long>>

    @GET("custom/created")
    fun getCreatedCalendar(): Call<RspModel<List<SimpleCalendarModel>>>
}
