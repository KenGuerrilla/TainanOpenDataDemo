package com.itl.kg.app.tainanopendatademo.repository

import androidx.lifecycle.MutableLiveData
import com.itl.kg.app.tainanopendatademo.module.ClientManager
import com.itl.kg.app.tainanopendatademo.module.DefaultObserverImp
import com.itl.kg.app.tainanopendatademo.module.LoadingMessageHandler
import com.itl.kg.app.tainanopendatademo.module.ObserverResponseListener
import com.itl.kg.app.tainanopendatademo.module.unit.ParkingResp

class ParkingRepository(
        private val loadingMessageHandler: LoadingMessageHandler
) {

    val parkingListLiveData: MutableLiveData<List<ParkingResp>> = MutableLiveData()

    fun requestFreeParkingList() {
        getFreeParkingListFromApi()
    }

    private fun getFreeParkingListFromApi() {
        ClientManager.getFreePublicParking(getFreeParkingListDefaultObs())
    }

    private fun getFreeParkingListFromLocal() {

    }

    private fun getFreeParkingListDefaultObs(): DefaultObserverImp<List<ParkingResp>> {
        return DefaultObserverImp(loadingMessageHandler,
                object : ObserverResponseListener<List<ParkingResp>> {
                    override fun onReceiveData(t: List<ParkingResp>) {
                        parkingListLiveData.value = t
                    }
                })
    }
}
