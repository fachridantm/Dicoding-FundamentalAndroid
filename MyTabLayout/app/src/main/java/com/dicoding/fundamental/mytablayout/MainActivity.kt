package com.dicoding.fundamental.mytablayout

import android.os.Bundle
import androidx.annotation.StringRes
import androidx.appcompat.app.AppCompatActivity
import androidx.viewpager2.widget.ViewPager2
import com.dicoding.fundamental.mytablayout.adapter.SectionsPagerAdapter
import com.dicoding.fundamental.mytablayout.databinding.ActivityMainBinding
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator

class MainActivity : AppCompatActivity() {

    private lateinit var mainBinding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        mainBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(mainBinding.root)

        val sectionsPagerAdapter = SectionsPagerAdapter(this)
        sectionsPagerAdapter.appName = resources.getString(R.string.app_name)

        val viewPager: ViewPager2 = mainBinding.viewPager
        viewPager.adapter = sectionsPagerAdapter

        val tabs: TabLayout = mainBinding.tabs
        TabLayoutMediator(tabs, viewPager) {
                tab, position -> tab.text = resources.getString(TAB_TITLES[position])
        }.attach()
        supportActionBar?.elevation = 0f
    }

    companion object {
        @StringRes
        private val TAB_TITLES = intArrayOf(
            R.string.tab_text_1,
            R.string.tab_text_2,
            R.string.tab_text_3
        )
    }
}