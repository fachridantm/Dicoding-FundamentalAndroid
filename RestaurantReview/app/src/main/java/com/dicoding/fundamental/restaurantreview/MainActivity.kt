package com.dicoding.fundamental.restaurantreview

import android.content.Context
import android.os.Bundle
import android.view.View
import android.view.inputmethod.InputMethodManager
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.dicoding.fundamental.restaurantreview.adapter.ReviewAdapter
import com.dicoding.fundamental.restaurantreview.api.CustomerReviewsItem
import com.dicoding.fundamental.restaurantreview.api.Restaurant
import com.dicoding.fundamental.restaurantreview.databinding.ActivityMainBinding
import com.dicoding.fundamental.restaurantreview.ui.MainViewModel
import com.google.android.material.snackbar.Snackbar

class MainActivity : AppCompatActivity() {

    private lateinit var mainBinding: ActivityMainBinding
    private val mainViewModel by viewModels<MainViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mainBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(mainBinding.root)

        supportActionBar?.hide()

//        val mainViewModel = ViewModelProvider(this, ViewModelProvider.NewInstanceFactory()).get(
//            MainViewModel::class.java
//        )
//        mainViewModel.restaurant.observe(this) { restaurant ->
//            setRestaurantData(restaurant)
//        }

        mainViewModel.restaurant.observe(this) { restaurant ->
            setRestaurantData(restaurant)
        }

        val layoutManager = LinearLayoutManager(this)
        mainBinding.rvReview.layoutManager = layoutManager

        val itemDecoration = DividerItemDecoration(this, layoutManager.orientation)
        mainBinding.rvReview.addItemDecoration(itemDecoration)

        mainViewModel.listReview.observe(this) { consumerReviews -> // consumerReviews = custom variabel from lambda
            setReviewData(consumerReviews)
        }
        mainViewModel.isLoading.observe(this) {
            showLoading(it) // it = default variabel from lambda
        }

        mainViewModel.snackbarText.observe(this) {
            it.getContentIfNotHandled()?.let { snackBarText ->
                Snackbar.make(
                    window.decorView.rootView,
                    snackBarText,
                    Snackbar.LENGTH_SHORT
                ).show()
            }
        }

        mainBinding.btnSend.setOnClickListener { view ->
            mainViewModel.postReview(mainBinding.edReview.text.toString())
            val imm = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            imm.hideSoftInputFromWindow(view.windowToken, 0)
        }
    }

    private fun setRestaurantData(restaurant: Restaurant) {
        mainBinding.tvTitle.text = restaurant.name
        mainBinding.tvDescription.text = restaurant.description
        Glide.with(this)
            .load("https://restaurant-api.dicoding.dev/images/large/${restaurant.pictureId}")
            .into(mainBinding.ivPicture)
    }

    private fun setReviewData(consumerReviews: List<CustomerReviewsItem>) {
        val listReview = consumerReviews.map {
            "${it.review}\n- ${it.name}"
        }
        val adapter = ReviewAdapter(listReview)
        mainBinding.rvReview.adapter = adapter
        mainBinding.edReview.setText("")
    }

    private fun showLoading(isLoading: Boolean) {
        mainBinding.progressBar.visibility = if (isLoading) View.VISIBLE else View.GONE
    }
}