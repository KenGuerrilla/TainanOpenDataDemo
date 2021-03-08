package com.itl.kg.app.tainanopendatademo.mvvm

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider


class ParkingViewModelFactory() : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return ParkingViewModel() as T
    }
}