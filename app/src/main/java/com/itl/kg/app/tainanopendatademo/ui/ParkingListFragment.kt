package com.itl.kg.app.tainanopendatademo.ui

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.itl.kg.app.tainanopendatademo.databinding.FragmentParkingListBinding
import com.itl.kg.app.tainanopendatademo.module.LoadingMessageHandler
import com.itl.kg.app.tainanopendatademo.mvvm.ParkingViewModel
import com.itl.kg.app.tainanopendatademo.mvvm.ParkingViewModelFactory

/**
 *
 *  資料以清單方式呈現，可做關鍵字分類
 *
 *  清單項目可點擊Intent至GoogleMap導航
 *
 *  需要實作一個機制，同時更新四組API資料，包含免費公有、收費公有以、民營以及路邊停車格
 *
 */

class ParkingListFragment : Fragment() {

    companion object {
        const val TAG = "ParkingListFragment"
    }

    private val parkingViewModel: ParkingViewModel by activityViewModels {
        ParkingViewModelFactory(LoadingMessageHandler(parentFragmentManager))
    }

    private var _binding: FragmentParkingListBinding? = null
    private val binding get() = _binding!!

    private val adapter = ParkingListAdapter(mutableListOf())

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentParkingListBinding.inflate(inflater, container , false)

        initListAdapter()

        initLiveData()

        return binding.root
    }

    private fun initLiveData() {
        parkingViewModel.freeParkingLiveData.observe(viewLifecycleOwner, Observer {
            Log.d(TAG, "initLiveData ${it.size}")
            adapter.list.addAll(it)
            adapter.notifyDataSetChanged()
        })
    }

    private fun initListAdapter() {
        val layoutManager = LinearLayoutManager(requireContext())

        binding.mParkingListRv.also {
            it.addItemDecoration(DividerItemDecoration(requireContext(), DividerItemDecoration.VERTICAL))
            it.layoutManager = layoutManager
            it.adapter = adapter
        }
    }

    override fun onResume() {
        super.onResume()
        parkingViewModel.getFreeParkingList()
    }

}