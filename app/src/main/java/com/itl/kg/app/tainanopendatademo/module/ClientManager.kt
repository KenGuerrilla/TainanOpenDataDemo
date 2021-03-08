package com.itl.kg.app.tainanopendatademo.module

import com.itl.kg.app.tainanopendatademo.module.unit.ParkingResp
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.core.Observer
import io.reactivex.rxjava3.schedulers.Schedulers
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory


object ClientManager {

    private val mApiService: ApiService

    init {
        val retrofit = Retrofit.Builder()
                .baseUrl(Config.PARKING_HOST)
                .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .client(createOkHttpClient())
                .build()

        mApiService = retrofit.create(ApiService::class.java)
    }


    private fun createOkHttpClient(): OkHttpClient {
        return OkHttpClient.Builder()
//            .addInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
            .build()
    }

    fun getFreePublicParking(observer: Observer<List<ParkingResp>>) {
        val observable = mApiService.getParkingInfo()
        toSubscribe(observable, observer)
    }

    private fun <T> toSubscribe(observable: Observable<T>, observer: Observer<T>) {
        observable.subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(observer)
    }

}