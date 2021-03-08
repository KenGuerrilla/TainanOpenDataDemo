package com.itl.kg.app.tainanopendatademo.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import com.itl.kg.app.tainanopendatademo.R
import com.itl.kg.app.tainanopendatademo.mvvm.ParkingViewModel
import com.itl.kg.app.tainanopendatademo.mvvm.ParkingViewModelFactory

class ParkingActivity : AppCompatActivity() {

    val parkingViewModel: ParkingViewModel by viewModels {
        ParkingViewModelFactory()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_parking)
    }

}