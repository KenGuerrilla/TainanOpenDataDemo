package com.itl.kg.app.tainanopendatademo.mvvm

import android.util.Log
import androidx.lifecycle.ViewModel
import com.itl.kg.app.tainanopendatademo.module.ClientManager
import io.reactivex.rxjava3.core.Observer
import io.reactivex.rxjava3.disposables.Disposable
import okhttp3.ResponseBody


class ParkingViewModel : ViewModel() {

    companion object {
        const val TAG = "ParkingViewModel"
    }

    fun getFreeParkingList() {
        ClientManager.getFreePublicParking(object : Observer<ResponseBody> {
            override fun onComplete() {
                Log.d(TAG, "onComplete")
            }

            override fun onSubscribe(d: Disposable?) {
                Log.d(TAG, "onSubscribe")
            }

            override fun onNext(t: ResponseBody) {
                Log.d(TAG, "onNext: ${t.string()}")
            }

            override fun onError(e: Throwable?) {
                Log.d(TAG, "onError")
            }
        })
    }

}