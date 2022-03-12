package com.demo.currencylist.viewModel

import android.app.Application
import android.content.Context
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.ViewModel
import com.demo.currencylist.dataModel.CurrencyInfo
import com.demo.currencylist.repo.CurrencyRepository
import io.reactivex.subjects.PublishSubject

class DemoActivityViewModel(application: Application) : AndroidViewModel(application) {

    var currencyInfoList: ArrayList<CurrencyInfo>? = null
    val publishListEvent = PublishSubject.create<ArrayList<CurrencyInfo>>()

    fun selectListItem(position: Int){
        Log.d("DemoActivityViewModel.selectListItem:", position.toString())
        Toast.makeText(getApplication(), currencyInfoList?.get(position)?.name, Toast.LENGTH_SHORT).show();
    }

    fun loadCurrencyList(){
        currencyInfoList = CurrencyRepository.getCurrencyList(getApplication())
        currencyInfoList?.let {
            publishListEvent.onNext(it)
        }
    }

    fun sortCurrencyList(){
        currencyInfoList?.let {
            it.sort()
            publishListEvent.onNext(it)
        }
    }
}