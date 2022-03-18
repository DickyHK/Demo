package com.demo.currencylist.room

import androidx.room.*
import com.demo.currencylist.dataModel.CurrencyInfo

@Dao
interface CurrencyInfoDao {
    @Query("SELECT * FROM currencyinfo")
    fun getAll(): List<CurrencyInfo>

    @Query("SELECT * FROM currencyinfo order by name")
    fun sortAll(): List<CurrencyInfo>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(infos: List<CurrencyInfo>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(vararg infos: CurrencyInfo)

    @Delete
    fun delete(info: CurrencyInfo)
}