package com.demo.currencylist.viewModel

import android.util.Log
import androidx.lifecycle.ViewModel
import com.demo.currencylist.dataModel.CurrencyInfo
import io.reactivex.subjects.PublishSubject

class CurrencyListFragmentViewModel : ViewModel(){

    var currencyInfoList: ArrayList<CurrencyInfo>? = null
    val publishListEvent = PublishSubject.create<ArrayList<CurrencyInfo>>()

    fun updateList(list: ArrayList<CurrencyInfo>){
        Log.d("CurrencyListViewModel", "updateList")
        currencyInfoList = list
        publishListEvent.onNext(currencyInfoList!!)
    }
}