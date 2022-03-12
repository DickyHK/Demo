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
import com.demo.currencylist.viewModel.CurrencyListFragmentViewModel
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.rxkotlin.addTo

class CurrencyListFragment : Fragment() {

    interface CurrencyListListener{
        fun onClick(position : Int)
    }

    lateinit var binding : FragmentCurrencyListBinding
    lateinit var adapter: CurrencyListAdapter
    lateinit var viewModel: CurrencyListFragmentViewModel
    lateinit var disposable: CompositeDisposable

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

        viewModel = ViewModelProvider(this).get(CurrencyListFragmentViewModel::class.java)
        disposable = CompositeDisposable()
        initView()
        subscribe()
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

    private fun subscribe(){
        viewModel.publishListEvent.subscribe {
            adapter.setData(it)
        }.addTo(disposable)
    }

    override fun onDestroy() {
        super.onDestroy()
        disposable.clear()
    }

    fun updateList(list: ArrayList<CurrencyInfo>){
        Log.d("CurrencyListFragment", "updateList")
        viewModel.updateList(list)
    }
}