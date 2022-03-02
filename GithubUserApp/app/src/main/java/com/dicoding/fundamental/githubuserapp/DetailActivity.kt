package com.dicoding.fundamental.githubuserapp

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.dicoding.fundamental.githubuserapp.databinding.ActivityDetailBinding

class DetailActivity : AppCompatActivity() {

    private var title: String = "Detail User"
    private lateinit var binding: ActivityDetailBinding

    companion object {
        const val EXTRA_NAME = "extra_name"
        const val EXTRA_USERNAME = "extra_username"
        const val EXTRA_AVATAR = "extra_avatar"
        const val EXTRA_FOLLOWING = "extra_following"
        const val EXTRA_FOLLOWERS = "extra_followers"
        const val EXTRA_COMPANY = "extra_company"
        const val EXTRA_LOCATION = "extra_location"
        const val EXTRA_REPOSITORY = "extra_repository"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar?.title = title

        val name = intent.getStringExtra(EXTRA_NAME)
        val username = intent.getStringExtra(EXTRA_USERNAME)
        val avatar = intent.getIntExtra(EXTRA_AVATAR, 0)
        val followers = intent.getStringExtra(EXTRA_FOLLOWERS)
        val following = intent.getStringExtra(EXTRA_FOLLOWING)
        val repository = intent.getStringExtra(EXTRA_REPOSITORY)
        val company = intent.getStringExtra(EXTRA_COMPANY)
        val location = intent.getStringExtra(EXTRA_LOCATION)

        binding.tvItemNameDetail.text = name
        binding.tvItemUsernameDetail.text = username
        binding.tvFollowers.text = followers
        binding.tvFollowing.text = following
        binding.tvRepository.text = repository
        binding.tvItemCompanyDetail.text = company
        binding.tvItemLocationDetail.text = location

        Glide.with(this)
            .load(avatar)
            .circleCrop()
            .into(binding.imgItemAvatarDetail)

        binding.btnShare.setOnClickListener {
            val intent = Intent(Intent.ACTION_SEND)
            intent.type = "text/plain"
            intent.putExtra(Intent.EXTRA_TEXT, "Follow $name on github! \nhttps://github.com/$username")
            startActivity(Intent.createChooser(intent, "Share with..."))
        }
    }
}