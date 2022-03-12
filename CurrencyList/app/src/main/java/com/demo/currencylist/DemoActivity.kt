package com.demo.currencylist

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.AttributeSet
import android.view.View
import androidx.databinding.DataBindingUtil
import com.demo.currencylist.databinding.ActivityMainBinding
import com.demo.currencylist.fragment.CurrencyListFragment

class DemoActivity : AppCompatActivity() {

    lateinit var binding : ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        val fragment = CurrencyListFragment()
        supportFragmentManager.beginTransaction().replace(R.id.container, fragment).commit()
    }
}