package com.ikhwankoto.samplerxjava2retrofit.network

import io.reactivex.Observable
import io.reactivex.Single
import okhttp3.RequestBody
import retrofit2.http.Body
import retrofit2.http.POST

interface ApiInterface {

    @POST("ikhwan/sample.php")
    fun getListDataHewanObservable(
            @Body body: RequestBody
    ): Observable<HewanDao>

    @POST("ikhwan/sample.php")
    fun getListDataHewanSingle(
            @Body body: RequestBody
    ): Single<HewanDao>

}