package com.demo.currencylist.repo

import android.content.Context
import android.util.Log
import com.demo.currencylist.dataModel.CurrencyInfo
import com.demo.currencylist.utils.CommonUtil
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

object CurrencyRepository {

    private fun retrieveCurrencyList(context : Context) : ArrayList<CurrencyInfo>{
        val jsonString = CommonUtil.getJsonFromAssets(context, "currencies.json")
        Log.i("CurrencyRepository.CurrencyJson: ", jsonString?:"")
        val gson = Gson()
        val type = object : TypeToken<List<CurrencyInfo>>() {}.type
        var currencies = gson.fromJson<List<CurrencyInfo>>(jsonString, type)
        return ArrayList(currencies)
    }

    fun getCurrencyList(context: Context): ArrayList<CurrencyInfo> {
        val list = retrieveCurrencyList(context)
        return list
    }
}