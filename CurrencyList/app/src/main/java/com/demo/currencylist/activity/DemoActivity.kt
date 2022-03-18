package com.demo.currencylist.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.demo.currencylist.R
import com.demo.currencylist.databinding.ActivityMainBinding
import com.demo.currencylist.fragment.CurrencyListFragment
import com.demo.currencylist.viewModel.DemoActivityViewModel

class DemoActivity : AppCompatActivity() {

    lateinit var binding : ActivityMainBinding
    lateinit var fragment : CurrencyListFragment
    lateinit var viewModel: DemoActivityViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        viewModel = ViewModelProvider(this).get(DemoActivityViewModel::class.java)

        binding.viewModel = viewModel
        binding.lifecycleOwner = this

        fragment = CurrencyListFragment()
        fragment.onClickListener = object: CurrencyListFragment.CurrencyListListener {
            override fun onClick(position: Int) {
                viewModel.selectListItem(position)
            }
        }
        supportFragmentManager.beginTransaction().replace(R.id.container, fragment).commit()

        viewModel.currencyInfoList.observe(this){
            fragment.updateList(it)
        }
    }

}