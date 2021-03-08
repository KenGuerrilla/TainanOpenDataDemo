package com.itl.kg.app.tainanopendatademo.module

import com.google.gson.annotations.SerializedName


data class ParkingListData (
    val version: String,
    val list: List<ParkingResp>
)

data class ParkingResp(
        @SerializedName("一般大型車") val standardFullSizeCar: Int,
        @SerializedName("一般小型車") val standardCompactCar: Int,
        @SerializedName("一般機車") val standardBike: Int,
        @SerializedName("停車場代碼") val codeName: String,
        @SerializedName("停車場名稱") val name: String,
        @SerializedName("停車場地址") val address: String,
        @SerializedName("停車場型態") val type: String,
        @SerializedName("即時車位") val realtimeSpace: Int,
        @SerializedName("婦幼者小型車") val parentChildSmallCar: Int,
        @SerializedName("收費時間") val chargePeriod: String,
        @SerializedName("收費費率") val charge: String,
        @SerializedName("經緯度") val latLng: String,
        @SerializedName("綠能小型車") val ecoSmallCar: Int,
        @SerializedName("身障者小型車") val disabledCompactCar: Int,
        @SerializedName("身障者機車") val disabledBike: Int
)

/**
    "停車場型態": "公有免費停車場",
    "停車場代碼": "0000",
    "停車場名稱": "城平路外免費停車場",
    "停車場地址": "臺南市安平區安平漁港管理中心兩側",
    "即時車位": -1,
    "一般大型車": 40,
    "一般小型車": 315,
    "身障者小型車": 0,
    "婦幼者小型車": 0,
    "綠能小型車": 0,
    "一般機車": 0,
    "身障者機車": 0,
    "收費時間": null,
    "收費費率": null,
    "經緯度": "22.993024，120.152138"
**/