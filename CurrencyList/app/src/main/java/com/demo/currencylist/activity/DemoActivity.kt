package com.demo.currencylist.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.demo.currencylist.R
import com.demo.currencylist.databinding.ActivityMainBinding
import com.demo.currencylist.ext.throttleClicks
import com.demo.currencylist.fragment.CurrencyListFragment
import com.demo.currencylist.viewModel.DemoActivityViewModel
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.rxkotlin.addTo

class DemoActivity : AppCompatActivity() {

    lateinit var binding : ActivityMainBinding
    lateinit var fragment : CurrencyListFragment
    lateinit var viewModel: DemoActivityViewModel
    lateinit var disposable: CompositeDisposable

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        viewModel = ViewModelProvider(this).get(DemoActivityViewModel::class.java)
        disposable = CompositeDisposable()

        initView()
        subscribe()
        viewModel.loadCurrencyList()
    }

    private fun initView(){
        binding.viewModel = viewModel
        binding.lifecycleOwner = this

        binding.loadBtn.throttleClicks().subscribe({
            Log.d("DemoActivity", "loadClick")
            viewModel.loadCurrencyList()
        }, {
            it.printStackTrace()
        }).addTo(disposable)

        binding.sortBtn.throttleClicks().subscribe({
            Log.d("DemoActivity", "sortClick")
            viewModel.sortCurrencyList()
        }, {
            it.printStackTrace()
        }).addTo(disposable)

        fragment = CurrencyListFragment()
        fragment.onClickListener = object: CurrencyListFragment.CurrencyListListener {
            override fun onClick(position: Int) {
                viewModel.selectListItem(position)
            }
        }
        supportFragmentManager.beginTransaction().replace(R.id.container, fragment).commit()
    }

    private fun subscribe(){
        viewModel.publishListEvent.subscribe {
            fragment.updateList(it)
        }.addTo(disposable)
    }

    override fun onDestroy() {
        super.onDestroy()
        disposable.clear()
    }
}