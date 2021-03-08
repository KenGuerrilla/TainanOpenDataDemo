package com.itl.kg.app.tainanopendatademo.module

import io.reactivex.rxjava3.core.Observable
import okhttp3.ResponseBody
import retrofit2.http.GET
import retrofit2.http.Query


interface ApiService {
    @GET("parking.ashx")
    fun getParkingInfo(
            @Query("verCode") verCode: String = "5177E3481D",
            @Query("type") type: String = "1",
            @Query("ftype") ftype: String = "1",
            @Query("exportTo") exportTo: String = "2"
    ): Observable<ResponseBody>

}