package com.dicoding.fundamental.githubfavoriteapp.ui.main

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.SearchView
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AlertDialog.Builder
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import androidx.recyclerview.widget.LinearLayoutManager
import com.dicoding.fundamental.githubfavoriteapp.R
import com.dicoding.fundamental.githubfavoriteapp.databinding.ActivityMainBinding
import com.dicoding.fundamental.githubfavoriteapp.helper.ViewModelFactory
import com.dicoding.fundamental.githubfavoriteapp.ui.favorite.FavoriteUserActivity


class MainActivity : AppCompatActivity() {

    private lateinit var mainBinding: ActivityMainBinding
    private lateinit var userQuery: String
    private lateinit var factory: ViewModelFactory
    private val mainViewModel: MainViewModel by viewModels { factory }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        mainBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(mainBinding.root)

        factory = ViewModelFactory.getInstance(this)

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

        mainViewModel.getThemeSetting().observe(this) { isNightMode ->
            if (isNightMode) {
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
            } else {
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
            }
        }
    }

    override fun onResume() {
        super.onResume()
        mainBinding.rvUsers.adapter = ListUserAdapter(emptyList())
    }

    private fun showRecyclerList() {
        mainBinding.rvUsers.apply {
            layoutManager = LinearLayoutManager(this@MainActivity)
            setHasFixedSize(true)
        }
    }

    private fun showLoading(isLoading: Boolean) {
        mainBinding.pbMain.visibility = if (isLoading) View.VISIBLE else View.GONE
    }

    private fun showImage(isImageVisible: Boolean) {
        mainBinding.ivDoodleFind.visibility = if (isImageVisible) View.VISIBLE else View.INVISIBLE
        mainBinding.tvDoodleFind.visibility = if (isImageVisible) View.VISIBLE else View.INVISIBLE
    }

    private fun themeDialog() {
        val modes = arrayOf("Dark Mode", "Light Mode")

        val builder = Builder(this)
        builder.setTitle("Select Theme Mode")
        builder.setItems(modes) { _, which ->
            mainViewModel.saveThemeSetting(which == 0)
            Toast.makeText(this, "Apply your theme to ${modes[which]}", Toast.LENGTH_SHORT).show()
        }
        builder.show()
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.main_menu, menu)

        val modeButton = menu.findItem(R.id.modeButton)
        mainViewModel.getThemeSetting().observe(this) { isNightMode ->
            if (isNightMode) modeButton.setIcon(R.drawable.ic_light_mode) else modeButton.setIcon(R.drawable.ic_night_mode)
        }

        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.favButton -> {
                val intent = Intent(this, FavoriteUserActivity::class.java)
                startActivity(intent)
                true
            }
            R.id.modeButton -> {
                themeDialog()
                true
            }
            else -> true
        }
    }
}