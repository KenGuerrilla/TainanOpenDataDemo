package com.itl.kg.app.tainanopendatademo.ui.map

import android.Manifest
import android.annotation.SuppressLint
import android.content.Intent
import android.location.Location
import android.location.LocationProvider
import androidx.fragment.app.Fragment

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap

import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.itl.kg.app.tainanopendatademo.R
import com.itl.kg.app.tainanopendatademo.databinding.FragmentParkingMapsBinding
import com.itl.kg.app.tainanopendatademo.mvvm.ParkingViewModel
import com.itl.kg.app.tainanopendatademo.mvvm.ParkingViewModelFactory
import com.itl.kg.app.tainanopendatademo.repository.ParkingListDatabase
import com.itl.kg.app.tainanopendatademo.repository.ParkingRepository
import com.itl.kg.app.tainanopendatademo.restfulApi.LoadingMessageHandler


/**
 *
 *  停車場地理位置
 *
 *  1. 顯示裝置位置
 *  2. 實作定位按鈕，將鏡頭移動到裝置位置
 *  3. 如無授權則隱藏右下角定位按鈕
 *  4. 檢查授權
 *
 *
 *  ps. 由於EasyPermission流程中需要使用到onActivityResult，但其被標註為Deprecated
 *      因此使用registerForActivityResult方案作為替代
 *
 */

class ParkingMapsFragment : Fragment() {

    companion object {
        private const val CAMERA_ZOOM_DEFAULT = 16f
        const val TAG = "ParkingMapsFragment"
    }

    private val parkingViewModel: ParkingViewModel by activityViewModels {
        val dao = ParkingListDatabase.getInstance(requireContext().applicationContext).parkingItemDao()
        val repository = ParkingRepository.getInstance(LoadingMessageHandler(parentFragmentManager), dao)
        ParkingViewModelFactory(repository)
    }

    private var _binding: FragmentParkingMapsBinding? = null
    private val binding get() = _binding!!

    private val permissionFineLocation = Manifest.permission.ACCESS_FINE_LOCATION
    private var mGoogleMap: GoogleMap? = null

    private var fusedLocationClient: FusedLocationProviderClient? = null

    private var resultLauncher = registerForActivityResult(ActivityResultContracts.RequestPermission()) {
        // 每次進來都會檢查有沒有權限
        if (it) {
            initLocationSetting()
        } else {
            Toast.makeText(requireContext(), "無授權定位權限", Toast.LENGTH_SHORT).show()
        }
    }


    private fun getMapReadyCallback(): OnMapReadyCallback {
        return OnMapReadyCallback { googleMap ->

            // 實作定位初始化 v
            // 顯示停車場地理位置
            // 實作點擊Mark顯示基本資訊 ( 進另一個Fragment )，內有導航按鈕

            mGoogleMap = googleMap

            parkingViewModel.parkingListLiveData.observe(viewLifecycleOwner, Observer{
//            Log.d(TAG, "initLiveData in map init: $it")
            })
            requestLocationPermission()
            initMapSetting()
        }
    }

    private fun requestLocationPermission() {
        resultLauncher.launch(permissionFineLocation)
    }

    private fun initMapSetting() {
        mGoogleMap?.uiSettings?.run {
            isMapToolbarEnabled = false
            isMyLocationButtonEnabled = false // Find My Current Button
            isRotateGesturesEnabled = false // 關閉視角轉動
        }
    }


    @SuppressLint("MissingPermission")
    private fun initLocationSetting() {
        binding.mLocationBtn.visibility = View.VISIBLE
        mGoogleMap?.isMyLocationEnabled = true // 裝置位置標記
        fusedLocationClient = LocationServices.getFusedLocationProviderClient(requireActivity())
        findMyLocation()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentParkingMapsBinding.inflate(inflater, container, false)
        initViewListener()
        return binding.root
    }

    @SuppressLint("MissingPermission")
    private fun initViewListener() {
        binding.mLocationBtn.setOnClickListener {
            findMyLocation()
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val mapFragment = childFragmentManager.findFragmentById(R.id.mGoogleMapFragment) as SupportMapFragment?
        mapFragment?.getMapAsync(getMapReadyCallback())
    }

    override fun onDestroy() {
        super.onDestroy()
        resultLauncher.unregister()
    }

    // 移動鏡頭
    private fun moveCameraWithAnimate(latLng: LatLng) {
        mGoogleMap?.animateCamera(CameraUpdateFactory.newLatLngZoom(latLng, CAMERA_ZOOM_DEFAULT))
    }

    // 移動鏡頭至裝置位置
    @SuppressLint("MissingPermission")
    private fun findMyLocation() {
        fusedLocationClient?.lastLocation?.run {
            addOnSuccessListener {location : Location? ->
                location?.run {
                    // move camera
                    moveCameraWithAnimate(LatLng(this.latitude, this.longitude))
                }
            }
        }
    }

}