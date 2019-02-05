package com.ikhwankoto.samplerxjava2retrofit.network

import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

class ApiClient {
    companion object {
        fun getBaseApiClient(baseUrl: String, readTimeOut: Long, connectTimeOut: Long, debug: Boolean): ApiInterface {
            val interceptor = HttpLoggingInterceptor()
            interceptor.level = HttpLoggingInterceptor.Level.HEADERS
            interceptor.level = HttpLoggingInterceptor.Level.BODY

            val builder = OkHttpClient.Builder()
            builder.readTimeout(readTimeOut, TimeUnit.SECONDS)
            builder.connectTimeout(connectTimeOut, TimeUnit.SECONDS)
            if (debug) {
                builder.addInterceptor(interceptor)
            }

            val client = builder.build()
            val gson = GsonBuilder().setLenient().create()
            val retrofit = Retrofit.Builder()
                    .client(client)
                    .baseUrl(baseUrl)
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .addConverterFactory(GsonConverterFactory.create(gson))
                    .build()
            return retrofit.create(ApiInterface::class.java)
        }
    }
}