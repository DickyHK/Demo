package com.demo.currencylist.repo

import android.app.Application
import android.content.Context
import android.util.Log
import com.demo.currencylist.dataModel.CurrencyInfo
import com.demo.currencylist.room.RoomDb
import com.demo.currencylist.utils.CommonUtil
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import org.json.JSONException

object CurrencyRepository {

    fun retrieveCurrencyList(application: Application) : Observable<Unit>{
        return Observable.create { emitter ->
            try {
                val jsonString = CommonUtil.getJsonFromAssets(application, "currencies.json")
                Log.i("CurrencyRepository.CurrencyJson: ", jsonString?:"")
                val gson = Gson()
                val type = object : TypeToken<List<CurrencyInfo>>() {}.type
                val currencies = gson.fromJson<List<CurrencyInfo>>(jsonString, type)
                RoomDb.getInstance().currencyInfoDao().insertAll(currencies)
                emitter.onNext(Unit)
                emitter.onComplete()
            } catch (e: JSONException) {
                e.printStackTrace()
                emitter.onError(Throwable("Cannot retrieve currency list"))
            }
        }
    }

    fun getCurrencyList(): Observable<ArrayList<CurrencyInfo>> {
        return Observable.create { emitter ->
            try {
                val list = RoomDb.getInstance().currencyInfoDao().getAll()
                emitter.onNext(ArrayList(list))
                emitter.onComplete()
            } catch (e: JSONException) {
                e.printStackTrace()
                emitter.onError(Throwable("Cannot get currency list from db"))
            }
        }
    }
}