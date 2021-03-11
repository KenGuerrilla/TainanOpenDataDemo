package com.itl.kg.app.tainanopendatademo.ui

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
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
import com.itl.kg.app.tainanopendatademo.restfulApi.LoadingMessageHandler
import com.itl.kg.app.tainanopendatademo.unit.ParkingResp
import com.itl.kg.app.tainanopendatademo.mvvm.ParkingViewModel
import com.itl.kg.app.tainanopendatademo.mvvm.ParkingViewModelFactory
import com.itl.kg.app.tainanopendatademo.repository.ParkingRepository
import com.itl.kg.app.tainanopendatademo.repository.ParkingListDatabase

/**
 *
 *  資料以清單方式呈現，可做關鍵字分類
 *
 *  可點擊項目進入DetailFragment
 *
 *  將停車場資料存入Local DB
 *
 *  進入頁面可檢查資料版本，需要實作資料版本號 ( 關連式資料庫 )
 *
 */

class ParkingListFragment : Fragment() {

    companion object {
        const val TAG = "ParkingListFragment"
    }

    private val parkingViewModel: ParkingViewModel by activityViewModels {
        // TODO 實作Hilt
        val dao = ParkingListDatabase.getInstance(requireContext().applicationContext).parkingItemDao()
        val repository = ParkingRepository.getInstance(LoadingMessageHandler(parentFragmentManager), dao)
        ParkingViewModelFactory(repository)
    }

    private var _binding: FragmentParkingListBinding? = null
    private val binding get() = _binding!!

    private val adapter = ParkingListAdapter(mutableListOf())

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentParkingListBinding.inflate(inflater, container , false)

        initEditTextListener()
        initListAdapter()
        initLiveData()

        return binding.root
    }

    private fun initEditTextListener() {
        binding.mSearchEt.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
                refreshAdapterList(parkingViewModel.searchItem(s.toString()))
            }
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}
        })
    }

    private fun refreshAdapterList(list: List<ParkingResp>) {
        adapter.list.clear()
        adapter.list.addAll(list)
        adapter.notifyDataSetChanged()
    }

    private fun initLiveData() {
        parkingViewModel.parkingListLiveData.observe(viewLifecycleOwner, Observer {
            if(it.isEmpty()) { // 如果資料庫是空的則更新資料庫資料
                parkingViewModel.updateFreeParkingList()
            } else {
                binding.mSearchEt.setText("")
                refreshAdapterList(it)
            }
        })
    }

    private fun initListAdapter() {
        val layoutManager = LinearLayoutManager(requireContext())

        adapter.setOnItemClick(initItemListener())

        binding.mParkingListRv.also {
            it.addItemDecoration(DividerItemDecoration(requireContext(), DividerItemDecoration.VERTICAL))
            it.layoutManager = layoutManager
            it.adapter = adapter
        }
    }

    private fun initItemListener(): ItemClickListener {
        return object : ItemClickListener{
            override fun onItemClick(view: View, item: ParkingResp) {
                startIntentToGoogleMap(item)
            }
        }
    }


    /**
     *
     * Start intent至GoogleMap - 直接丟網址塞參數給GoogleMap處理
     *
     * 參考資料：https://developers.google.com/maps/documentation/urls/get-started#directions-action
     *
     */

    private fun startIntentToGoogleMap(item: ParkingResp) {
        val latLng = parkingViewModel.parserItemLatLng(item.latLng)
        val gmmIntentUri =
                Uri.parse("https://www.google.com/maps/dir/?api=1&destination=${latLng.first},${latLng.second}&travelmode=driving")
        val mapIntent = Intent(Intent.ACTION_VIEW, gmmIntentUri)
        mapIntent.setPackage("com.google.android.apps.maps")
        startActivity(mapIntent)
    }

}