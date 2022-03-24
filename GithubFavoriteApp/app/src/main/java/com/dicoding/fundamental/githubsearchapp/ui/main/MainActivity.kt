package com.dicoding.fundamental.githubsearchapp.ui.main

import android.os.Bundle
import android.view.View
import android.widget.SearchView
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.dicoding.fundamental.githubsearchapp.adapter.ListUserAdapter
import com.dicoding.fundamental.githubsearchapp.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity() {

    private lateinit var mainBinding: ActivityMainBinding
    private val mainViewModel by viewModels<MainViewModel>()
    private lateinit var userQuery: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        mainBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(mainBinding.root)

        mainBinding.svUser.apply {
            setOnQueryTextListener(object : SearchView.OnQueryTextListener {
                override fun onQueryTextSubmit(query: String?): Boolean {
                    userQuery = query.toString()
                    clearFocus()
                    val getData = mainViewModel.getUser(userQuery)
                    if (userQuery.isEmpty() || getData.equals(null)) {
                        mainBinding.rvUsers.adapter = ListUserAdapter(emptyList())
                        showImage(true)
                    } else {
                        showImage(false)
                    }
                    return true
                }

                override fun onQueryTextChange(newText: String?): Boolean {
                    userQuery = newText.toString()
                    if (userQuery.isEmpty()) {
                        mainBinding.rvUsers.adapter = ListUserAdapter(emptyList())
                        showImage(true)
                    } else {
                        mainBinding.rvUsers.adapter = ListUserAdapter(emptyList())
                        showImage(false)
                    }
                    return true
                }
            })
        }

        mainViewModel.toastText.observe(this) {
            it.getContentIfNotHandled()?.let { toastText ->
                Toast.makeText(
                    this, toastText, Toast.LENGTH_SHORT
                ).show()
            }
        }

        showRecyclerList()
        mainViewModel.listGithubUser.observe(this) { listGithubUser ->
            mainBinding.rvUsers.adapter = ListUserAdapter(listGithubUser)
        }

        mainViewModel.isLoading.observe(this) {
            showLoading(it)
        }
    }

    private fun showRecyclerList() {
        mainBinding.rvUsers.apply {
            layoutManager = LinearLayoutManager(this@MainActivity)
            setHasFixedSize(true)
        }
    }

    private fun showLoading(isLoading: Boolean) {
        mainBinding.progressBar.visibility = if (isLoading) View.VISIBLE else View.GONE
    }

    private fun showImage(isImageVisible: Boolean) {
        mainBinding.ivDoodleFind.visibility = if (isImageVisible) View.VISIBLE else View.INVISIBLE
        mainBinding.tvDoodleFind.visibility = if (isImageVisible) View.VISIBLE else View.INVISIBLE
    }
}