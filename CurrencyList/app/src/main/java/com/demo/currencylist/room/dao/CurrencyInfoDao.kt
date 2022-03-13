package com.demo.currencylist.room.dao

import androidx.room.*
import com.demo.currencylist.dataModel.CurrencyInfo

@Dao
interface CurrencyInfoDao {
    @Query("SELECT * FROM currencyinfo")
    fun getAll(): List<CurrencyInfo>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(infos: List<CurrencyInfo>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(vararg infos: CurrencyInfo)

    @Delete
    fun delete(info: CurrencyInfo)
}