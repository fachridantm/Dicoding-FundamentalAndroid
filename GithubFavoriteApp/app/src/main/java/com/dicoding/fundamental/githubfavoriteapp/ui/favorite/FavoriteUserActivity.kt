package com.dicoding.fundamental.githubfavoriteapp.ui.favorite

import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.dicoding.fundamental.githubfavoriteapp.R
import com.dicoding.fundamental.githubfavoriteapp.databinding.ActivityFavoriteUserBinding
import com.dicoding.fundamental.githubfavoriteapp.helper.ViewModelFactory

class FavoriteUserActivity : AppCompatActivity() {

    private lateinit var favoriteUserBinding: ActivityFavoriteUserBinding
    private lateinit var factory: ViewModelFactory
    private lateinit var favoriteAdapter: FavoriteAdapter
    private val favoriteViewModel: FavoriteViewModel by viewModels { factory }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        favoriteUserBinding = ActivityFavoriteUserBinding.inflate(layoutInflater)
        setContentView(favoriteUserBinding.root)

        supportActionBar?.apply {
            title = getString(R.string.title_favorite)
            setDisplayHomeAsUpEnabled(true)
        }

        factory = ViewModelFactory.getInstance(this)

        favoriteAdapter = FavoriteAdapter()
        showRecyclerList()
        favoriteViewModel.getFavoritedUser().observe(this) { favList ->
            favoriteUserBinding.progressBarFav.visibility = View.GONE
            favoriteAdapter.updateUserList(favList)

            val isListEmpty = favList.isEmpty()
            if (isListEmpty) {
                showImage(isListEmpty)
            } else {
                showImage(isListEmpty)
            }
        }
    }

    private fun showRecyclerList() {
        favoriteUserBinding.rvFavorite.apply {
            layoutManager = LinearLayoutManager(this@FavoriteUserActivity)
            setHasFixedSize(true)
            adapter = favoriteAdapter
        }
    }

    private fun showImage(isImageVisible: Boolean) {
        favoriteUserBinding.ivDoodleFav.visibility =
            if (isImageVisible) View.VISIBLE else View.INVISIBLE
        favoriteUserBinding.tvDoodleFav.visibility =
            if (isImageVisible) View.VISIBLE else View.INVISIBLE
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return super.onSupportNavigateUp()
    }
}