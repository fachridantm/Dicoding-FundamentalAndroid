package com.dicoding.fundamental.githubuserapp

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.dicoding.fundamental.githubuserapp.DetailActivity.Companion.EXTRA_USER
import com.dicoding.fundamental.githubuserapp.databinding.ItemRowUserBinding

class ListUserAdapter(private val listUser: ArrayList<GithubUser>) : RecyclerView.Adapter<ListUserAdapter.ListViewHolder>() {

    inner class ListViewHolder(private val bindingItemRowUserBinding: ItemRowUserBinding) :
        RecyclerView.ViewHolder(bindingItemRowUserBinding.root) {

        fun bind(user: GithubUser) {
            bindingItemRowUserBinding.apply {
                tvItemUsername.text = user.username
                tvItemName.text = user.name
            }

            Glide.with(itemView.context)
                .load(user.avatar)
                .apply(RequestOptions.circleCropTransform())
                .into(bindingItemRowUserBinding.imgItemAvatar)

            itemView.setOnClickListener() {
                val intent = Intent(itemView.context, DetailActivity::class.java)
                intent.putExtra(EXTRA_USER, user)
                itemView.context.startActivity(intent)
            }

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListViewHolder {
        val bindingItemRowUserBinding =
            ItemRowUserBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ListViewHolder(bindingItemRowUserBinding)
    }

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        holder.bind(listUser[position])
    }

    override fun getItemCount(): Int = listUser.size
}