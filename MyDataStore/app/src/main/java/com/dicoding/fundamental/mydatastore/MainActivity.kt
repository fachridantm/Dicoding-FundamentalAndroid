package com.dicoding.fundamental.mydatastore

import android.content.Context
import android.os.Bundle
import android.widget.CompoundButton
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.preferencesDataStore
import androidx.lifecycle.ViewModelProvider
import com.dicoding.fundamental.mydatastore.databinding.ActivityMainBinding

private val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "settings")

class MainActivity : AppCompatActivity() {

    private lateinit var mainBinding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mainBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(mainBinding.root)

        val pref = SettingPreferences.getInstance(dataStore)
        val mainViewModel =
            ViewModelProvider(this, ViewModelFactory(pref))[MainViewModel::class.java]

        mainBinding.apply {
            mainViewModel.getThemeSettings().observe(this@MainActivity) { isDarkModeActive: Boolean ->
                if (isDarkModeActive) {
                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
                    switchTheme.isChecked = true
                } else {
                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
                    switchTheme.isChecked = false
                }

            }
            switchTheme.setOnCheckedChangeListener { _: CompoundButton?, isChecked: Boolean ->
                mainViewModel.saveThemeSetting(isChecked)
            }
        }
    }
}