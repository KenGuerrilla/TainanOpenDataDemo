package com.itl.kg.app.tainanopendatademo.repository

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.itl.kg.app.tainanopendatademo.unit.ParkingResp


// version的用意是當資料表的欄位格式改變時，版本就要進一版
@Database(entities = [ParkingResp::class], version = 1)
abstract class ParkingListDatabase() : RoomDatabase() {

    abstract fun parkingItemDao(): ParkingListDao

    companion object {

        @Volatile
        private var instance: ParkingListDatabase? = null

        fun getInstance(context: Context): ParkingListDatabase {
            return instance
                ?: synchronized(this) {
                instance
                    ?: buildDatabase(
                        context
                    )
                        .also { instance = it }
            }
        }

        private fun buildDatabase(context: Context): ParkingListDatabase {
            return Room.databaseBuilder(context, ParkingListDatabase::class.java, "parkingDatabase")
                .addCallback( object : RoomDatabase.Callback() {
                    // call back
                })
                .build()
        }

    }

}