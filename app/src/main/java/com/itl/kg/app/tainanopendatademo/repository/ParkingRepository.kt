package com.itl.kg.app.tainanopendatademo.repository

import androidx.lifecycle.LiveData
import com.itl.kg.app.tainanopendatademo.restfulApi.ClientManager
import com.itl.kg.app.tainanopendatademo.restfulApi.DefaultObserverImp
import com.itl.kg.app.tainanopendatademo.restfulApi.LoadingMessageHandler
import com.itl.kg.app.tainanopendatademo.restfulApi.ObserverResponseListener
import com.itl.kg.app.tainanopendatademo.unit.ParkingResp
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ParkingRepository private constructor(
        private val loadingMessageHandler: LoadingMessageHandler,
        private val dao: ParkingListDao
) {

    // LiveData會在註冊之後立刻回應DB的資料
    val freeParkingListLiveData: LiveData<List<ParkingResp>> = dao.getFreeParkingListLiveData()

    fun updateFreeParkingList() {
        updateParkingList()
    }

    private fun updateParkingList() {
        ClientManager.getFreePublicParking(getFreeParkingListDefaultObs())
    }

    private fun getFreeParkingListDefaultObs(): DefaultObserverImp<List<ParkingResp>> {
        return DefaultObserverImp(loadingMessageHandler,
                object : ObserverResponseListener<List<ParkingResp>> {
                    override fun onReceiveData(t: List<ParkingResp>) {

                        val sop = CoroutineScope(Dispatchers.IO)
                        sop.launch {
                            dao.insertAllFreeParkingList(t)
                        }
                    }
                })
    }

    companion object {

        const val TAG = "ParkingRepository"

        @Volatile
        private var instance: ParkingRepository? = null

        fun getInstance(loadingMessageHandler: LoadingMessageHandler, dao: ParkingListDao) = instance ?: synchronized(this) {
            instance ?: ParkingRepository(loadingMessageHandler, dao).also { instance = it }
        }
    }
}
