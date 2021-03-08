package com.itl.kg.app.tainanopendatademo.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import com.itl.kg.app.tainanopendatademo.R
import com.itl.kg.app.tainanopendatademo.databinding.FragmentParkingListBinding
import com.itl.kg.app.tainanopendatademo.mvvm.ParkingViewModel
import com.itl.kg.app.tainanopendatademo.mvvm.ParkingViewModelFactory

/**
 *
 *  資料以清單方式呈現，可做關鍵字分類
 *
 *  清單項目可點擊Intent至GoogleMap導航
 *
 */

class ParkingListFragment : Fragment() {

    val parkingViewModel: ParkingViewModel by activityViewModels {
        ParkingViewModelFactory()
    }

    private var _binding: FragmentParkingListBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentParkingListBinding.inflate(inflater, container , false)

        return binding.root
    }

    override fun onResume() {
        super.onResume()
        parkingViewModel.getFreeParkingList()
    }

}