package com.dicoding.fundamental.githubfavoriteapp.ui

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import com.dicoding.fundamental.githubfavoriteapp.databinding.ActivitySplashScreenBinding
import com.dicoding.fundamental.githubfavoriteapp.helper.ViewModelFactory
import com.dicoding.fundamental.githubfavoriteapp.ui.main.MainActivity
import com.dicoding.fundamental.githubfavoriteapp.ui.main.MainViewModel

@SuppressLint("CustomSplashScreen")
class SplashScreenActivity : AppCompatActivity() {

    private lateinit var bindingSplash: ActivitySplashScreenBinding
    private lateinit var factory: ViewModelFactory
    private val mainViewModel: MainViewModel by viewModels { factory }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        bindingSplash = ActivitySplashScreenBinding.inflate(layoutInflater)
        setContentView(bindingSplash.root)

        supportActionBar?.hide()
        factory = ViewModelFactory.getInstance(this)
        mainViewModel.getThemeSetting().observe(this) { isNightMode ->
            if (isNightMode) {
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
            } else {
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
            }
        }

        val handler = Handler(mainLooper)
        handler.postDelayed({
            val intent = Intent(this@SplashScreenActivity, MainActivity::class.java)
            startActivity(intent)
            finish()
        }, DELAY_TIME)
    }

    companion object {
        private const val DELAY_TIME = 1500L
    }
}