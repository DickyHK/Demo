package com.demo.currencylist.activity

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.demo.currencylist.R
import com.demo.currencylist.viewModel.DemoActivityViewModel
import com.demo.currencylist.viewModel.SplashActivityViewModel
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.rxkotlin.addTo

class SplashActivity : AppCompatActivity() {

    lateinit var viewModel : SplashActivityViewModel
    lateinit var disposable: CompositeDisposable

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        viewModel = ViewModelProvider(this).get(SplashActivityViewModel::class.java)
        disposable = CompositeDisposable()

        subscribe()
        viewModel.start()
    }

    private fun subscribe(){
        viewModel.finishLoadingEvent.subscribe{
            val intent = Intent(this, DemoActivity::class.java)
            startActivity(intent)
        }.addTo(disposable)
    }
}