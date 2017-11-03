package com.hrsoft.today.network

import com.hrsoft.today.common.Config
import com.hrsoft.today.mvp.model.CalendarModel
import com.hrsoft.today.mvp.model.User
import okhttp3.OkHttpClient
import okhttp3.Response
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
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

    @GET("calendar/subscribed/1")
    fun requestCalendarList(): Call<RspModel<List<CalendarModel>>>
}
