package com.itl.kg.app.tainanopendatademo.mvvm

import androidx.lifecycle.*
import com.itl.kg.app.tainanopendatademo.unit.ParkingResp
import com.itl.kg.app.tainanopendatademo.repository.ParkingRepository

class ParkingViewModel(
        private val repository: ParkingRepository
) : ViewModel() {

    companion object {
        const val TAG = "ParkingViewModel"
    }

    private val sourceList = mutableListOf<ParkingResp>()

    val parkingListLiveData = MediatorLiveData<List<ParkingResp>>().apply {

        // 當來自Local的資料變動時就setValue，並另外存一份在ViewModel為sourceList
        addSource(repository.freeParkingListLiveData) {
                value ->
            this.value = value
            sourceList.clear()
            sourceList.addAll(value)
        }
    }

    // 使用SourceList為基底進行篩選
    fun searchItem(keyword: String): List<ParkingResp> {

        val buffer = mutableSetOf<ParkingResp>()

        sourceList.forEach {
            when {
                it.address.contains(keyword) -> buffer.add(it)
                it.name.contains(keyword) -> buffer.add(it)
            }
        }

        return if (keyword.isEmpty()) sourceList else buffer.toList()
    }

    fun parserItemLatLng(latLng: String): Pair<String, String> {
        val buffer = latLng.split("，")
        return Pair(buffer[0], buffer[1])
    }

    fun updateFreeParkingList() {
        repository.updateFreeParkingList()
    }
}