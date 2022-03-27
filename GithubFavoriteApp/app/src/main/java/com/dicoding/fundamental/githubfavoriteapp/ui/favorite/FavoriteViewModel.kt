package com.dicoding.fundamental.githubfavoriteapp.ui.favorite

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dicoding.fundamental.githubfavoriteapp.data.UserRepository
import com.dicoding.fundamental.githubfavoriteapp.data.model.entity.UserEntity
import kotlinx.coroutines.launch

class FavoriteViewModel(private val repository: UserRepository) : ViewModel() {

    fun getFavoritedUser() = repository.getFavoritedUser()

    fun saveDeleteUser(user: UserEntity, isFavorited: Boolean) {
        viewModelScope.launch {
            if (isFavorited) {
                repository.deleteFavoriteUser(user, false)
            } else {
                repository.addFavoriteUser(user, true)
            }
        }
    }
}