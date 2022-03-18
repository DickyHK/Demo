package com.demo.currencylist.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.demo.currencylist.dataModel.CurrencyInfo
import com.demo.currencylist.databinding.LayoutCurrencyListItemBinding

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
        when(holder){
            is ItemHolder -> {
                holder.binding.name.text = data[position].name
                holder.binding.symbol.text = data[position].symbol
                if(!data[position].name.isNullOrEmpty()){
                    holder.binding.icon.text = (data[position].name!![0].toString())
                }
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