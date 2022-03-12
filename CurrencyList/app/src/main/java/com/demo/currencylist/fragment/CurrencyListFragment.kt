package com.demo.currencylist.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Adapter
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.demo.currencylist.adapter.CurrencyListAdapter
import com.demo.currencylist.dataModel.CurrencyInfo
import com.demo.currencylist.databinding.FragmentCurrencyListBinding

class CurrencyListFragment : Fragment() {

    interface Listener{
        fun onItemClickListener()
    }

    lateinit var binding : FragmentCurrencyListBinding
    lateinit var adapter: CurrencyListAdapter
    var currencyInfoList: ArrayList<CurrencyInfo>? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentCurrencyListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initView()
    }


    private fun initView(){
        adapter = CurrencyListAdapter()
        binding.recyclerView.layoutManager = LinearLayoutManager(context)
        binding.recyclerView.adapter = adapter
        currencyInfoList?.let {
            adapter.setData(it)
        }
    }

    fun updateList(list: List<CurrencyInfo>){
        currencyInfoList = ArrayList(list)
        currencyInfoList?.let {
            adapter.setData(it)
        }
    }
}