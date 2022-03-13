package com.demo.currencylist.repo

import android.content.Context
import android.util.Log
import com.demo.currencylist.dataModel.CurrencyInfo
import com.demo.currencylist.utils.CommonUtil
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import org.json.JSONException

object CurrencyRepository {

    fun retrieveCurrencyList(context : Context) : Observable<Unit>{
        return Observable.create { emitter ->
            try {
                val jsonString = CommonUtil.getJsonFromAssets(context, "currencies.json")
                Log.i("CurrencyRepository.CurrencyJson: ", jsonString?:"")
                val gson = Gson()
                val type = object : TypeToken<List<CurrencyInfo>>() {}.type
                var currencies = gson.fromJson<List<CurrencyInfo>>(jsonString, type)
                emitter.onNext(Unit)
                emitter.onComplete()
            } catch (e: JSONException) {
                e.printStackTrace()
                emitter.onError(Throwable("Cannot retrieve currency list"))
            }
        }
    }

    fun getCurrencyList(context: Context): ArrayList<CurrencyInfo> {
        val list = ArrayList<CurrencyInfo>()
        return list
    }
}