package com.example.ozinshe.data

import android.content.Context
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object ServiceBuilder {
    private val client = OkHttpClient.Builder().build()

    private val retrofit = Retrofit.Builder()
        .baseUrl("http://api.ozinshe.com")
        .addConverterFactory(GsonConverterFactory.create())
        .client(client)
        .build()

    fun <T> buildService(service: Class<T>): T{
        return retrofit.create(service)
    }

//    fun <T> buildService(service: Class<T>, context: Context): T {
//
//        val authInterceptor = Interceptor { chain ->
//            val sharedPref = context.getSharedPreferences("MyAppPrefs", Context.MODE_PRIVATE)
//            val token = sharedPref.getString("accessToken", null)
//
//            val requestBuilder = chain.request().newBuilder()
//            if (!token.isNullOrEmpty()) {
//                requestBuilder.addHeader("Authorization", "Bearer $token")
//            }
//
//            chain.proceed(requestBuilder.build())
//        }
//
//        val client = OkHttpClient.Builder()
//            .addInterceptor(authInterceptor)
//            .build()
//
//        val retrofit = Retrofit.Builder()
//            .baseUrl("http://api.ozinshe.com/")
//            .addConverterFactory(GsonConverterFactory.create())
//            .client(client)
//            .build()
//
//        return retrofit.create(service)
//    }
}