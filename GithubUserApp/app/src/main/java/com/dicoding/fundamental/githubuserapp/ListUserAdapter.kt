package com.dicoding.fundamental.githubuserapp

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.dicoding.fundamental.githubuserapp.databinding.ActivityDetailBinding
import com.dicoding.fundamental.githubuserapp.databinding.ItemRowUserBinding

class ListUserAdapter(private val listUser: ArrayList<GithubUser>) :
    RecyclerView.Adapter<ListUserAdapter.ListViewHolder>() {

    class ListViewHolder(var bindingItemRowUserBinding: ItemRowUserBinding) : RecyclerView.ViewHolder(bindingItemRowUserBinding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListViewHolder {
        val bindingItemRowUserBinding = ItemRowUserBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ListViewHolder(bindingItemRowUserBinding)
    }

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        val (name, username, avatar, following, followers, repository, company, location) = listUser[position]

        Glide.with(holder.itemView.context)
            .load(avatar)
            .circleCrop()
            .into(holder.bindingItemRowUserBinding.imgItemAvatar)
        holder.bindingItemRowUserBinding.tvItemName.text = name
        holder.bindingItemRowUserBinding.tvItemUsername.text = username

        val intent = holder.itemView.context
        holder.itemView.setOnClickListener {
            val moveDetail = Intent(intent, DetailActivity::class.java)
            moveDetail.putExtra(DetailActivity.EXTRA_NAME, name)
            moveDetail.putExtra(DetailActivity.EXTRA_USERNAME, username)
            moveDetail.putExtra(DetailActivity.EXTRA_AVATAR, avatar)
            moveDetail.putExtra(DetailActivity.EXTRA_FOLLOWING, following)
            moveDetail.putExtra(DetailActivity.EXTRA_FOLLOWERS, followers)
            moveDetail.putExtra(DetailActivity.EXTRA_REPOSITORY, repository)
            moveDetail.putExtra(DetailActivity.EXTRA_COMPANY, company)
            moveDetail.putExtra(DetailActivity.EXTRA_LOCATION, location)
            intent.startActivity(moveDetail)
        }
    }

    override fun getItemCount(): Int = listUser.size
}