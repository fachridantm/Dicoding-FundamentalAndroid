package com.dicoding.fundamental.mylivedata

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.dicoding.fundamental.mylivedata.databinding.ActivityMainBinding
import com.dicoding.fundamental.mylivedata.ui.MainViewModel

class MainActivity : AppCompatActivity() {

    private lateinit var mLiveDataTimerViewModel: MainViewModel
    private lateinit var mainBinding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mainBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(mainBinding.root)

        mLiveDataTimerViewModel = ViewModelProvider(this)[MainViewModel::class.java]
        subscribe()
    }

    private fun subscribe() {
        val elapsedTimeObserver = Observer<Long?> { aLong ->
            val newText = this@MainActivity.resources.getString(R.string.seconds, aLong)
            mainBinding.timerTextview.text = newText
        }
        mLiveDataTimerViewModel.getElapsedTime().observe(this, elapsedTimeObserver)
    }
}