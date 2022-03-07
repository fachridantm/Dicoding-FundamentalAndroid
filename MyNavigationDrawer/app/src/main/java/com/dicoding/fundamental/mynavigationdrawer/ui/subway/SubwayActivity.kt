package com.dicoding.fundamental.mynavigationdrawer.ui.subway

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.dicoding.fundamental.mynavigationdrawer.databinding.ActivitySubwayBinding

class SubwayActivity : AppCompatActivity() {

    private lateinit var bindingSubway: ActivitySubwayBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        bindingSubway = ActivitySubwayBinding.inflate(layoutInflater)
        setContentView(bindingSubway.root)
    }
}