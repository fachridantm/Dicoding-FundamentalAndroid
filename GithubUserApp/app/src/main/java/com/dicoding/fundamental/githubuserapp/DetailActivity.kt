package com.dicoding.fundamental.githubuserapp

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.dicoding.fundamental.githubuserapp.databinding.ActivityDetailBinding

class DetailActivity : AppCompatActivity() {

    private var newTitle: String = "Detail User"
    private lateinit var binding: ActivityDetailBinding

    companion object {
        const val EXTRA_USER = "extra_user"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar?.apply {
            title = newTitle
            setDisplayHomeAsUpEnabled(true)
        }

        val user = intent.getParcelableExtra<GithubUser>(EXTRA_USER) as GithubUser

        binding.apply {
            tvItemUsernameDetail.text = user.username
            tvItemNameDetail.text = user.name
            tvItemLocationDetail.text = user.location
            tvRepository.text = user.repository
            tvItemCompanyDetail.text = user.company
            tvFollowers.text = user.followers
            tvFollowing.text = user.following
        }

        Glide.with(this)
            .load(user.avatar)
            .apply(RequestOptions.circleCropTransform())
            .into(binding.imgItemAvatarDetail)


        binding.btnShare.setOnClickListener {
            val intent = Intent(Intent.ACTION_SEND)
            val shareUser = "Follow ${user.name} on github! \nhttps://github.com/${user.username}"
            intent.type = "text/plain"
            intent.putExtra(Intent.EXTRA_TEXT, shareUser)
            startActivity(Intent.createChooser(intent, "Share with..."))
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return super.onSupportNavigateUp()
    }
}