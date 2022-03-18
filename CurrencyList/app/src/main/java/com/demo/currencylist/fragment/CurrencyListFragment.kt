package com.demo.currencylist.fragment

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.demo.currencylist.adapter.CurrencyListAdapter
import com.demo.currencylist.dataModel.CurrencyInfo
import com.demo.currencylist.databinding.FragmentCurrencyListBinding
import com.demo.currencylist.viewModel.DemoActivityViewModel
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.rxkotlin.addTo

class CurrencyListFragment : Fragment() {

    interface CurrencyListListener{
        fun onClick(position : Int)
    }

    lateinit var binding : FragmentCurrencyListBinding
    lateinit var adapter: CurrencyListAdapter

    var onClickListener: CurrencyListListener? = null

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
        adapter = CurrencyListAdapter().apply {
            this.onItemClickListener = object : CurrencyListAdapter.OnItemClickListener{
                override fun onItemClicked(position: Int) {
                    onClickListener?.onClick(position)
                }
            }
        }
        binding.recyclerView.layoutManager = LinearLayoutManager(context)
        binding.recyclerView.adapter = adapter
    }

    fun updateList(list : ArrayList<CurrencyInfo>){
        adapter.setData(list)
    }
}