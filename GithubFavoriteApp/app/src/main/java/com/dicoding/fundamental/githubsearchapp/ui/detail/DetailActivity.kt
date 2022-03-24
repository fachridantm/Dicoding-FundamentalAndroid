package com.dicoding.fundamental.githubsearchapp.ui.detail

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.dicoding.fundamental.githubsearchapp.R
import com.dicoding.fundamental.githubsearchapp.adapter.DetailAdapter
import com.dicoding.fundamental.githubsearchapp.databinding.ActivityDetailBinding
import com.dicoding.fundamental.githubsearchapp.ui.follow.FollowFragment
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator

class DetailActivity : AppCompatActivity() {

    private lateinit var detailBinding: ActivityDetailBinding
    private val detailViewModel by viewModels<DetailViewModel>()
    private lateinit var usernameShare: String
    private lateinit var urlShare: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        detailBinding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(detailBinding.root)

        supportActionBar?.apply {
            title = getString(R.string.title_detail)
            setDisplayHomeAsUpEnabled(true)
        }

        val username = intent.getStringExtra(EXTRA_USER)
        detailViewModel.getDetailUser(username)

        detailViewModel.userData.observe(this) { userData ->
            detailBinding.apply {
                tvUsername.text = userData.username
                tvName.text = userData?.name ?: "-"
                tvLocation.text = userData?.location ?: "-"
                tvCompany.text = userData?.company ?: "-"

                val shortFollowers = userData.followers
                if (shortFollowers > 10000) {
                    "${shortFollowers / 1000}.${(shortFollowers % 1000) / 100}K".also {
                        tvFollowers.text = it
                    }
                } else {
                    tvFollowers.text = userData.followers.toString()
                }

                val shortFollowing = userData.following
                if (shortFollowing > 10000) {
                    "${shortFollowing / 1000}.${(shortFollowing % 1000) / 100}K".also {
                        tvFollowing.text = it
                    }
                } else {
                    tvFollowing.text = userData.following.toString()
                }

                val shortRepository = userData.repository
                if (shortRepository > 10000) {
                    "${shortRepository / 1000}.${(shortRepository % 1000) / 100}K".also {
                        tvRepository.text = it
                    }
                } else {
                    tvRepository.text = userData.repository.toString()
                }

                Glide.with(this@DetailActivity)
                    .load(userData.avatar)
                    .apply(RequestOptions
                        .circleCropTransform()
                        .placeholder(R.drawable.logo_github_light)
                        .error(R.drawable.logo_github_light)
                    )
                    .into(ivAvatar)

                val fragment = mutableListOf<Fragment>(
                    FollowFragment.newInstance(FollowFragment.FOLLOWING),
                    FollowFragment.newInstance(FollowFragment.FOLLOWERS)
                )

                val fragmentTitle = mutableListOf(
                    getString(R.string.following),
                    getString(R.string.followers)
                )

                val detailAdapter = DetailAdapter(this@DetailActivity, fragment)
                viewPager.adapter = detailAdapter

                TabLayoutMediator(tabLayout, viewPager) { tab, position ->
                    tab.text = fragmentTitle[position]
                }.attach()

                tabLayout.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
                    override fun onTabSelected(tab: TabLayout.Tab?) {
                        if (tab?.position == FollowFragment.FOLLOWERS) {
                            detailViewModel.getFollowers(userData.username)
                        } else {
                            detailViewModel.getFollowing(userData.username)
                        }
                    }

                    override fun onTabUnselected(tab: TabLayout.Tab?) {}
                    override fun onTabReselected(tab: TabLayout.Tab?) {}
                })
                detailViewModel.getFollowing(userData.username)
            }
            usernameShare = userData.username.toString()
            urlShare = userData.url.toString()
        }

        detailViewModel.toastText.observe(this) {
            it.getContentIfNotHandled()?.let { toastText ->
                Toast.makeText(
                    this, toastText, Toast.LENGTH_SHORT
                ).show()
            }
        }

        detailViewModel.isLoading.observe(this) {
            showLoading(it)
        }
    }

    private fun showLoading(isLoading: Boolean) {
        detailBinding.progressBar.visibility = if (isLoading) View.VISIBLE else View.GONE
    }

    companion object {
        const val EXTRA_USER = "extra_user"
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return super.onSupportNavigateUp()
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.main_menu, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        setMode(item.itemId)
        return super.onOptionsItemSelected(item)
    }

    private fun setMode(selectedMode: Int) {
        when (selectedMode) {
            R.id.shareButton -> {
                val intent = Intent(Intent.ACTION_SEND)
                val shareUser = "Follow $usernameShare on github! \n$urlShare"
                intent.type = "text/plain"
                intent.putExtra(Intent.EXTRA_TEXT, shareUser)
                startActivity(Intent.createChooser(intent, "Share with..."))
            }
        }
    }
}