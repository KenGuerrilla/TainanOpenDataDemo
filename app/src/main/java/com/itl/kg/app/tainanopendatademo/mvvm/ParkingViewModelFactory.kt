package com.itl.kg.app.tainanopendatademo.mvvm

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.itl.kg.app.tainanopendatademo.repository.ParkingRepository

// TODO 用Hilt實作DI
class ParkingViewModelFactory(
        private val repository: ParkingRepository
) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return ParkingViewModel(repository) as T
    }
}