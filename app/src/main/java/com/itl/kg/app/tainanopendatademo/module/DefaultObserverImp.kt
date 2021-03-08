package com.itl.kg.app.tainanopendatademo.module

import android.util.Log
import io.reactivex.rxjava3.observers.DefaultObserver

class DefaultObserverImp <T>(
    private val loadingHandler: LoadingMessageHandler,
    private val responseListener: ObserverResponseListener<T>
) : DefaultObserver<T>(), ProgressCancelInterface {

    companion object {
        const val TAG = "DefaultObserverImp"
    }

    override fun onStart() {
        super.onStart()
        loadingHandler.showLoadingView()
    }

    override fun onNext(t: T?) {
        t?.run {
            responseListener.onReceiveData(t)
        }
    }

    override fun onComplete() {
        // dialog dismiss
        loadingHandler.dismissLoadingView()
    }

    // TODO 如果資料在GSON Adapter轉換錯誤時無法呼叫這個方法，待釐清
    override fun onError(e: Throwable?) {
        Log.d(TAG, "onError: ${e?.localizedMessage}")
        loadingHandler.dismissLoadingView()
    }

    override fun cancelObserver() {
        this.cancel()
    }

}

interface ObserverResponseListener <T> {
    fun onReceiveData(t: T)
}

interface ProgressCancelInterface {
    fun cancelObserver()
}