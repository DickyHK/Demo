package com.demo.currencylist.viewModel

import android.app.Application
import android.content.Context
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.ViewModel
import com.demo.currencylist.dataModel.CurrencyInfo
import com.demo.currencylist.repo.CurrencyRepository
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.rxkotlin.addTo
import io.reactivex.schedulers.Schedulers
import io.reactivex.subjects.PublishSubject

class DemoActivityViewModel(application: Application) : AndroidViewModel(application) {

    val disposable: CompositeDisposable by lazy {
        CompositeDisposable()
    }

    var currencyInfoList: ArrayList<CurrencyInfo>? = null
    val publishListEvent = PublishSubject.create<ArrayList<CurrencyInfo>>()

    fun selectListItem(position: Int){
        Log.d("DemoActivityViewModel.selectListItem:", position.toString())
        Toast.makeText(getApplication(), currencyInfoList?.get(position)?.name, Toast.LENGTH_SHORT).show();
    }

    fun loadCurrencyList(){
        CurrencyRepository.getCurrencyList().subscribeOn(Schedulers.newThread())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                currencyInfoList = it
                currencyInfoList?.let {
                    publishListEvent.onNext(it)
                }
            }, {
                it.printStackTrace()
            }).addTo(disposable)
    }

    fun sortCurrencyList(){
        CurrencyRepository.getCurrencyList().subscribeOn(Schedulers.newThread())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                currencyInfoList = it
                currencyInfoList?.let {
                    it.sort()
                    publishListEvent.onNext(it)
                }
            }, {
                it.printStackTrace()
            }).addTo(disposable)
    }

    override fun onCleared() {
        super.onCleared()
        disposable.clear()
    }
}