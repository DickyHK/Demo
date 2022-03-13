package com.demo.currencylist.viewModel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.demo.currencylist.dataModel.CurrencyInfo
import com.demo.currencylist.repo.CurrencyRepository
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.rxkotlin.addTo
import io.reactivex.schedulers.Schedulers
import io.reactivex.subjects.PublishSubject

class SplashActivityViewModel(application: Application) : AndroidViewModel(application) {

    val disposable: CompositeDisposable by lazy {
        CompositeDisposable()
    }
    val finishLoadingEvent = PublishSubject.create<Unit>()

    fun start(){
        CurrencyRepository.retrieveCurrencyList(getApplication())
            .subscribeOn(Schedulers.newThread())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                finishLoadingEvent.onNext(Unit)
            }, {
                it.printStackTrace()
            }).addTo(disposable)
    }

    override fun onCleared() {
        super.onCleared()
        disposable.clear()
    }
}