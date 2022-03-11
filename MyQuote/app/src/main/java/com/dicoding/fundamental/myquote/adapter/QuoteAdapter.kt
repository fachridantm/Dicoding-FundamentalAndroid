package com.dicoding.fundamental.myquote.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.dicoding.fundamental.myquote.databinding.ItemQuoteBinding

class QuoteAdapter(private val listQuote: ArrayList<String>) :
    RecyclerView.Adapter<QuoteAdapter.ListViewHolder>() {

    inner class ListViewHolder(val itemBinding: ItemQuoteBinding) :
        RecyclerView.ViewHolder(itemBinding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListViewHolder {
        val itemBinding =
            ItemQuoteBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ListViewHolder(itemBinding)
    }

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        holder.itemBinding.tvItem.text = listQuote[position]
    }

    override fun getItemCount(): Int = listQuote.size
}