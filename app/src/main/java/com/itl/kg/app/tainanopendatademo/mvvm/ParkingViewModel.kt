package com.itl.kg.app.tainanopendatademo.mvvm

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.itl.kg.app.tainanopendatademo.module.unit.ParkingResp
import com.itl.kg.app.tainanopendatademo.repository.ParkingRepository

class ParkingViewModel(
        private val repository: ParkingRepository
) : ViewModel() {

    companion object {
        const val TAG = "ParkingViewModel"
    }

    fun getFreeParkingLiveData(): LiveData<List<ParkingResp>> = repository.parkingListLiveData

    fun getFreeParkingList() {
        repository.requestFreeParkingList()
    }

    fun parserItemLatLng(latLng: String): Pair<String, String> {
        val buffer = latLng.split("，")
        return Pair(buffer[0], buffer[1])
    }
}