package com.dicoding.fundamental.githubfavoriteapp.ui.follow

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.dicoding.fundamental.githubfavoriteapp.R
import com.dicoding.fundamental.githubfavoriteapp.databinding.ItemRowUserBinding
import com.dicoding.fundamental.githubfavoriteapp.data.model.response.FollowResponseItem
import com.dicoding.fundamental.githubfavoriteapp.ui.detail.DetailActivity

class FollowAdapter(private val listUser: ArrayList<FollowResponseItem>) :
    RecyclerView.Adapter<FollowAdapter.ViewPagerHolder>() {

    inner class ViewPagerHolder(private val userBinding: ItemRowUserBinding) :
        RecyclerView.ViewHolder(userBinding.root) {

        fun bind(user: FollowResponseItem) {
            userBinding.apply {
                tvItemUsername.text = user.username
                Glide.with(itemView.context)
                    .load(user.avatar)
                    .apply(
                        RequestOptions
                            .circleCropTransform()
                            .placeholder(R.drawable.ic_loading)
                            .error(R.drawable.ic_error)
                    )
                    .into(ivItemAvatar)
            }

            itemView.setOnClickListener {
                val intent = Intent(itemView.context, DetailActivity::class.java)
                intent.putExtra(DetailActivity.EXTRA_USER, user.username)
                itemView.context.startActivity(intent)
            }

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewPagerHolder {
        val userBinding =
            ItemRowUserBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewPagerHolder(userBinding)
    }

    override fun onBindViewHolder(holder: ViewPagerHolder, position: Int) {
        holder.bind(listUser[position])
    }

    override fun getItemCount(): Int = listUser.size
}