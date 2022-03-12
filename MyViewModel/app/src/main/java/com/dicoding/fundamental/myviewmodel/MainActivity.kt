package com.dicoding.fundamental.myviewmodel

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.dicoding.fundamental.myviewmodel.databinding.ActivityMainBinding
import com.dicoding.fundamental.myviewmodel.ui.MainViewModel

class MainActivity : AppCompatActivity() {

    private lateinit var mainBinding: ActivityMainBinding

    //    private lateinit var viewModel: MainViewModel
    private val viewModel: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        mainBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(mainBinding.root)
//        viewModel = ViewModelProvider(this)[MainViewModel::class.java]
        displayResult()
        mainBinding.btnCalculate.setOnClickListener {
            val width = mainBinding.edtWidth.text.toString()
            val height = mainBinding.edtHeight.text.toString()
            val length = mainBinding.edtLength.text.toString()
            when {
                width.isEmpty() -> {
                    mainBinding.edtWidth.error = "Masih kosong"
                }
                height.isEmpty() -> {
                    mainBinding.edtHeight.error = "Masih kosong"
                }
                length.isEmpty() -> {
                    mainBinding.edtLength.error = "Masih kosong"
                }
                else -> {
                    viewModel.calculate(width, height, length)
                    displayResult()
                }
            }
        }
    }

    private fun displayResult() {
        mainBinding.tvResult.text = viewModel.result.toString()
    }
}