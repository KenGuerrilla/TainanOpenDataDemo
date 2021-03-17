package com.itl.kg.app.tainanopendatademo.unit

import androidx.room.Entity
import com.google.gson.annotations.SerializedName


/**
 *
 *  這組API資料有點麻煩，原因是能夠當鍵值的codeName欄位會出現相同的字串，且並非統一的格式
 *  因此使用多組欄位來作為複合鍵值(composite primary key)使用，可參考下列文件
 *
 *  參考文件：https://developer.android.com/training/data-storage/room/defining-data
 *
 *  如果API回應的內容欄位有null，欄位變數要允許為null
 *
 */

@Entity(
    tableName = "freeParkingListTable",
    primaryKeys = ["codeName", "name"]
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
        @SerializedName("收費時間") val chargePeriod: String?,
        @SerializedName("收費費率") val charge: String?,
        @SerializedName("經緯度") val latLng: String?,
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