package com.dicoding.fundamental.githubfavoriteapp.ui.detail

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.dicoding.fundamental.githubfavoriteapp.data.api.ApiConfig
import com.dicoding.fundamental.githubfavoriteapp.helper.Event
import com.dicoding.fundamental.githubfavoriteapp.data.model.response.FollowResponseItem
import com.dicoding.fundamental.githubfavoriteapp.data.model.response.GithubUser
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class DetailViewModel : ViewModel() {

    private val _listGithubUser = MutableLiveData<ArrayList<FollowResponseItem>>()
    val listGithubUser: LiveData<ArrayList<FollowResponseItem>> = _listGithubUser

    private val _userData = MutableLiveData<GithubUser>()
    val userData: LiveData<GithubUser> = _userData

    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> = _isLoading

    private val _toastText = MutableLiveData<Event<String>>()
    val toastText: LiveData<Event<String>> = _toastText

    fun getDetailUser(username: String?) {
        _isLoading.value = true
        val client = ApiConfig.getApiService().getDetailUser(username)
        client.enqueue(object : Callback<GithubUser> {
            override fun onResponse(
                call: Call<GithubUser>,
                response: Response<GithubUser>
            ) {
                _isLoading.value = false
                if (response.isSuccessful) {
                    _userData.postValue(response.body())
                } else {
                    _toastText.value = Event("Something went wrong. ${response.message()}")
                    Log.e(TAG, "onFailure: ${response.message()}")
                }
            }

            override fun onFailure(call: Call<GithubUser>, t: Throwable) {
                _isLoading.value = false
                _toastText.value = Event("Something went wrong. ${t.message.toString()}")
                Log.e(TAG, "onFailure: ${t.message.toString()}")
            }
        })
    }

    fun getFollowers(username: String?) {
        _isLoading.value = true
        val client = ApiConfig.getApiService().getUserFollowers(username)
        client.enqueue(object : Callback<ArrayList<FollowResponseItem>> {
            override fun onResponse(
                call: Call<ArrayList<FollowResponseItem>>,
                response: Response<ArrayList<FollowResponseItem>>
            ) {
                _isLoading.value = false
                if (response.isSuccessful) {
                    _listGithubUser.value = response.body()
                } else {
                    _toastText.value = Event("Something went wrong. ${response.message()}")
                    Log.e(TAG, "onFailure: ${response.message()}")
                }
            }

            override fun onFailure(call: Call<ArrayList<FollowResponseItem>>, t: Throwable) {
                _isLoading.value = false
                _toastText.value = Event("Something went wrong. ${t.message.toString()}")
                Log.e(TAG, "onFailure: ${t.message.toString()}")
            }
        })
    }

    fun getFollowing(username: String?) {
        _isLoading.value = true
        val client = ApiConfig.getApiService().getUserFollowing(username)
        client.enqueue(object : Callback<ArrayList<FollowResponseItem>> {
            override fun onResponse(
                call: Call<ArrayList<FollowResponseItem>>,
                response: Response<ArrayList<FollowResponseItem>>
            ) {
                _isLoading.value = false
                if (response.isSuccessful) {
                    _listGithubUser.value = response.body()
                } else {
                    _toastText.value = Event("Something went wrong. ${response.message()}")
                    Log.e(TAG, "onFailure: ${response.message()}")
                }
            }

            override fun onFailure(call: Call<ArrayList<FollowResponseItem>>, t: Throwable) {
                _isLoading.value = false
                _toastText.value = Event("Something went wrong. ${t.message.toString()}")
                Log.e(TAG, "onFailure: ${t.message.toString()}")
            }
        })
    }

    companion object {
        private const val TAG = "DetailViewModel"
    }
}