package com.dicoding.fundamental.restaurantreview.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.dicoding.fundamental.restaurantreview.databinding.ItemReviewBinding

class ReviewAdapter(private val listReview: List<String>) :
    RecyclerView.Adapter<ReviewAdapter.ListViewHolder>() {

    inner class ListViewHolder(val itemBinding: ItemReviewBinding) :
        RecyclerView.ViewHolder(itemBinding.root)

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ListViewHolder {
        val itemBinding =
            ItemReviewBinding.inflate(LayoutInflater.from(viewGroup.context), viewGroup, false)
        return ListViewHolder(itemBinding)
    }

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        holder.itemBinding.tvItem.text = listReview[position]
    }

    override fun getItemCount() = listReview.size
}