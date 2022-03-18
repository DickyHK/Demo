package com.demo.currencylist.viewModel

import android.app.Application
import android.content.Context
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.demo.currencylist.dataModel.CurrencyInfo
import com.demo.currencylist.room.RoomDb
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.rxkotlin.addTo
import io.reactivex.schedulers.Schedulers
import io.reactivex.subjects.PublishSubject

class DemoActivityViewModel(application: Application) : AndroidViewModel(application) {

    val disposable: CompositeDisposable by lazy {
        CompositeDisposable()
    }

    var currencyInfoList = MutableLiveData<ArrayList<CurrencyInfo>>(ArrayList())

    init {
        RoomDb.retrieveCurrencyList(getApplication())
            .subscribeOn(Schedulers.newThread())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe().addTo(disposable)
    }

    fun selectListItem(position: Int){
        Log.d("DemoActivityViewModel.selectListItem:", position.toString())
        currencyInfoList.value?.get(position)?.name?.let {
            Toast.makeText(getApplication(), it, Toast.LENGTH_SHORT)
        }
    }

    fun onLoadClicked(view : View){
        RoomDb.getCurrencyList()
            .subscribeOn(Schedulers.newThread())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                currencyInfoList.value = it
            }, {
                it.printStackTrace()
            }).addTo(disposable)
    }

    fun onSortClicked(view : View){
        RoomDb.getSortedCurrencyList()
            .subscribeOn(Schedulers.newThread())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                currencyInfoList.value = it
            }, {
                it.printStackTrace()
            }).addTo(disposable)
    }

    override fun onCleared() {
        super.onCleared()
        disposable.clear()
    }
}