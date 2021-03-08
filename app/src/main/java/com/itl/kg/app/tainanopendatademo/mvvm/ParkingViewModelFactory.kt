package com.itl.kg.app.tainanopendatademo.mvvm

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.itl.kg.app.tainanopendatademo.module.LoadingMessageHandler

// TODO 用Hilt實作DI
class ParkingViewModelFactory(
        private val loadingMessageHandler: LoadingMessageHandler
) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return ParkingViewModel(loadingMessageHandler) as T
    }
}