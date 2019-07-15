package by.itechart.android.data.api

import by.itechart.android.data.entities.User
import okhttp3.OkHttpClient
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query
import java.util.concurrent.TimeUnit

interface FacebookService {

    @GET("/me?fields=id,email,name")
    fun getProfile(@Query(value = "access_token", encoded = true) token: String): Call<User>

    object Factory {
        fun create(): FacebookService {
            val okHttpClientBuilder = OkHttpClient.Builder()
                .connectTimeout(20, TimeUnit.SECONDS)

            val retrofit = Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl("https://graph.facebook.com/")
                .client(okHttpClientBuilder.build())
                .build()

            return retrofit.create(FacebookService::class.java)
        }
    }
}