package com.itl.kg.app.tainanopendatademo.repository

import androidx.lifecycle.LiveData
import androidx.room.*
import com.itl.kg.app.tainanopendatademo.unit.ParkingResp


/**
 *
 *  Database操作介面
 *
 */

@Dao
interface ParkingListDao {

    @Query("SELECT * FROM freeParkingListTable")
    fun getFreeParkingList() : LiveData<List<ParkingResp>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAllFreeParkingList(list: List<ParkingResp>)

    @Update
    fun updateFreeParkingList(list: List<ParkingResp>)

}