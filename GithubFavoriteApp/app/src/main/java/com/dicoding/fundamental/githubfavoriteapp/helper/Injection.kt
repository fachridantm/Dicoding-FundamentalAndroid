package com.dicoding.fundamental.githubfavoriteapp.helper

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.preferencesDataStore
import com.dicoding.fundamental.githubfavoriteapp.data.UserRepository
import com.dicoding.fundamental.githubfavoriteapp.data.api.ApiConfig
import com.dicoding.fundamental.githubfavoriteapp.data.room.UserDatabase

private val Context.dataStore: DataStore<androidx.datastore.preferences.core.Preferences> by preferencesDataStore(
    "settings"
)

object Injection {
    fun provideRepository(context: Context): UserRepository {
        val apiService = ApiConfig.getApiService()
        val database = UserDatabase.getInstance(context)
        val dao = database.userDao()
        val preferences = SettingPreferences.getInstance(context.dataStore)
        return UserRepository.getInstance(preferences, apiService, dao)
    }
}