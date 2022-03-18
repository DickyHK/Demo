package com.demo.currencylist.room

import android.app.Application
import android.content.Context
import android.util.Log
import androidx.room.Room
import com.demo.currencylist.dataModel.CurrencyInfo
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import io.reactivex.Observable
import org.json.JSONException
import java.io.IOException

object RoomDb {

    private lateinit var instance: AppDatabase

    fun initDB(application: Application){
        instance = Room.databaseBuilder(
            application.applicationContext,
            AppDatabase::class.java, "demoDb"
        ).build()
    }

    private fun getInstance() : AppDatabase{
        Log.d("RoomDb","get db instance")
        return instance
    }

    private fun getJsonFromAssets(context: Context, fileName: String): String? {
        val jsonString: String
        try {
            jsonString = context.assets.open(fileName).bufferedReader().use { it.readText() }
        } catch (ioException: IOException) {
            ioException.printStackTrace()
            return null
        }
        return jsonString
    }

    fun retrieveCurrencyList(application: Application) : Observable<Unit> {
        return Observable.create { emitter ->
            try {
                val jsonString = getJsonFromAssets(application, "currencies.json")
                Log.i("CurrencyRepository.CurrencyJson: ", jsonString?:"")
                val gson = Gson()
                val type = object : TypeToken<List<CurrencyInfo>>() {}.type
                val currencies = gson.fromJson<List<CurrencyInfo>>(jsonString, type)
                getInstance().currencyInfoDao().insertAll(currencies)
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
                val list = getInstance().currencyInfoDao().getAll()
                emitter.onNext(ArrayList(list))
                emitter.onComplete()
            } catch (e: JSONException) {
                e.printStackTrace()
                emitter.onError(Throwable("Cannot get currency list from db"))
            }
        }
    }

    fun getSortedCurrencyList(): Observable<ArrayList<CurrencyInfo>> {
        return Observable.create { emitter ->
            try {
                val list = getInstance().currencyInfoDao().sortAll()
                emitter.onNext(ArrayList(list))
                emitter.onComplete()
            } catch (e: JSONException) {
                e.printStackTrace()
                emitter.onError(Throwable("Cannot get currency list from db"))
            }
        }
    }
}