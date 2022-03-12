package com.demo.currencylist.dataModel

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class CurrencyInfo(
    @PrimaryKey val id: String,
    @ColumnInfo(name = "name") val name: String?,
    @ColumnInfo(name = "symbol") val symbol: String?
) : Comparable<CurrencyInfo> {
    override fun compareTo(other: CurrencyInfo): Int {
        val name = this.name?:""
        val compareName = other.name?:""
        return name.compareTo(compareName)
    }
}