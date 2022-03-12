package com.demo.currencylist.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.demo.currencylist.dataModel.CurrencyInfo
import com.demo.currencylist.databinding.LayoutCurrencyListItemBinding
import com.demo.currencylist.viewModel.CurrencyListItemViewModel

class CurrencyListAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    interface OnItemClickListener {
        fun onItemClicked(position: Int)
    }

    var data = ArrayList<CurrencyInfo>()
    var onItemClickListener: OnItemClickListener? = null

    fun setData(data : List<CurrencyInfo>){
        this.data = ArrayList(data)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return ItemHolder(LayoutCurrencyListItemBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val itemViewModel = CurrencyListItemViewModel()
        when(holder){
            is ItemHolder -> {
                itemViewModel.name.set(data[position].name)
                itemViewModel.symbol.set(data[position].symbol)
                data[position].name?.let {
                    if(it.isNotEmpty()){
                        itemViewModel.icon.set(data[position].name!!.get(0).toString())
                    }
                }
                holder.binding.viewModel = itemViewModel
                holder.binding.setOnClickListener {
                    onItemClickListener?.onItemClicked(position)
                }
                holder.binding.executePendingBindings()
            }
        }
    }

    override fun getItemCount(): Int {
        return data.size
    }

    class ItemHolder(var binding: LayoutCurrencyListItemBinding) : RecyclerView.ViewHolder(binding.root)
}