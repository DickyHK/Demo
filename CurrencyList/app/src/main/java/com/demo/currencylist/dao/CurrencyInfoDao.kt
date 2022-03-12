package com.demo.currencylist.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.demo.currencylist.dataModel.CurrencyInfo

@Dao
interface CurrencyInfoDao {
    @Query("SELECT * FROM currencyinfo")
    fun getAll(): List<CurrencyInfo>

    @Insert
    fun insertAll(vararg infos: CurrencyInfo)

    @Delete
    fun delete(info: CurrencyInfo)
}