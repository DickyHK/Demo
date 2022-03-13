package com.demo.currencylist.room

import androidx.room.Database
import androidx.room.RoomDatabase
import com.demo.currencylist.dataModel.CurrencyInfo
import com.demo.currencylist.room.dao.CurrencyInfoDao

@Database(entities = [CurrencyInfo::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun currencyInfoDao(): CurrencyInfoDao
}