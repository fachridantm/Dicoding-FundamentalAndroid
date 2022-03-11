package com.dicoding.fundamental.mybackgroundthread

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.dicoding.fundamental.mybackgroundthread.databinding.ActivityMainBinding
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MainActivity : AppCompatActivity() {

    private lateinit var mainBinding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mainBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(mainBinding.root)

        val btnStart = mainBinding.btnStart
        val tvStatus = mainBinding.tvStatus

//        val executor = Executors.newSingleThreadExecutor()
//        val handler = Handler(Looper.getMainLooper())

        btnStart.setOnClickListener {
            /* Handler + Executor */
//            executor.execute {
            /* Coroutines */
            lifecycleScope.launch(Dispatchers.Default) {
//                try {
                //simulate process in background thread
                for (i in 0..10) {
//                    Thread.sleep(500)
                    delay(500)
                    val percentage = i * 10
                    withContext(Dispatchers.Main) {
//                    handler.post {}
                        //update ui in main thread
                        if (percentage == 100) {
                            tvStatus.setText(R.string.task_completed)
                        } else {
                            tvStatus.text =
                                String.format(getString(R.string.compressing), percentage)
                        }
                    }
                }
//                } catch (e: InterruptedException) {
//                    e.printStackTrace()
//                }
            }
        }
    }
}