package com.demo.currencylist.viewModel

import android.app.Application
import android.content.Context
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.demo.currencylist.dataModel.CurrencyInfo
import com.demo.currencylist.repo.CurrencyRepository
import com.demo.currencylist.utils.ToastUtil
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

    var currencyInfoList: ArrayList<CurrencyInfo>? = null
    var enableBtn: MutableLiveData<Boolean> = MutableLiveData<Boolean>(true)
    val publishListEvent = PublishSubject.create<ArrayList<CurrencyInfo>>()
    var asyncTriggered = false

    fun selectListItem(position: Int){
        Log.d("DemoActivityViewModel.selectListItem:", position.toString())
        currencyInfoList?.get(position)?.name?.let {
            ToastUtil.showShortToast(getApplication(), it)
        }
    }

    fun loadCurrencyList(){
        if(!asyncTriggered){
            CurrencyRepository.getCurrencyList()
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnSubscribe { asyncTriggered = true }
                .doOnTerminate { asyncTriggered = false }
                .subscribe({
                    currencyInfoList = it
                    currencyInfoList?.let {
                        publishListEvent.onNext(it)
                    }
                }, {
                    it.printStackTrace()
                }).addTo(disposable)
        }
    }

    fun sortCurrencyList(){
        if(!asyncTriggered) {
            CurrencyRepository.getSortedCurrencyList()
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnSubscribe { asyncTriggered = true }
                .doOnTerminate { asyncTriggered = false }
                .subscribe({
                    currencyInfoList = it
                    currencyInfoList?.let {
                        publishListEvent.onNext(it)
                    }
                }, {
                    it.printStackTrace()
                }).addTo(disposable)
        }
    }

    override fun onCleared() {
        super.onCleared()
        disposable.clear()
    }
}