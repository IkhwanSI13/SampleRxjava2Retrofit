package com.ikhwankoto.samplerxjava2retrofit.network

import com.google.gson.JsonObject
import io.reactivex.Observable
import io.reactivex.Single
import okhttp3.MediaType
import okhttp3.RequestBody

class HewanRepo {
    companion object {

        val BASE_URL = "http://ikhwankoto.com/"

        fun getApiInterface(): ApiInterface {
            return ApiClient.getBaseApiClient(BASE_URL, 10, 10, true)
        }

        fun getHewanWithObservable(): Observable<HewanDao> {
            val jsonObject = JsonObject()
            jsonObject.addProperty("id", 1)
            return getApiInterface().getListDataHewanObservable(RequestBody.create(MediaType.parse("application/json"),
                    jsonObject.toString()))
        }

        fun getHewanWithSingle(): Single<HewanDao> {
            val jsonObject = JsonObject()
            jsonObject.addProperty("id", 1)
            return getApiInterface().getListDataHewanSingle(RequestBody.create(MediaType.parse("application/json"),
                    jsonObject.toString()))
        }
    }
}