package com.demo.currencylist.room.dao

import androidx.room.*
import com.demo.currencylist.dataModel.CurrencyInfo

@Dao
interface CurrencyInfoDao {
    @Query("SELECT * FROM currencyinfo")
    fun getAll(): List<CurrencyInfo>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(vararg infos: List<CurrencyInfo>)

    @Delete
    fun delete(info: CurrencyInfo)
}