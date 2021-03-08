package com.itl.kg.app.tainanopendatademo.mvvm

import androidx.lifecycle.MutableLiveData

import androidx.lifecycle.ViewModel
import com.itl.kg.app.tainanopendatademo.module.ClientManager
import com.itl.kg.app.tainanopendatademo.module.DefaultObserverImp
import com.itl.kg.app.tainanopendatademo.module.LoadingMessageHandler
import com.itl.kg.app.tainanopendatademo.module.ObserverResponseListener
import com.itl.kg.app.tainanopendatademo.module.unit.ParkingResp

class ParkingViewModel(
        private val loadingMessageHandler: LoadingMessageHandler
) : ViewModel() {

    companion object {
        const val TAG = "ParkingViewModel"
    }

    val freeParkingLiveData: MutableLiveData<List<ParkingResp>> = MutableLiveData()

    fun getFreeParkingList() {
        ClientManager.getFreePublicParking(getFreeParkingListDefaultObs())
    }


    private fun getFreeParkingListDefaultObs(): DefaultObserverImp<List<ParkingResp>> {
        return DefaultObserverImp(loadingMessageHandler,
                object : ObserverResponseListener<List<ParkingResp>> {
                    override fun onReceiveData(t: List<ParkingResp>) {
                        freeParkingLiveData.value = t
                    }
                })
    }

}