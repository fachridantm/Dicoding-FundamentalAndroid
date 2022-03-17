package com.dicoding.fundamental.githubsearchapp.api

import com.dicoding.fundamental.githubsearchapp.BuildConfig
import com.dicoding.fundamental.githubsearchapp.model.FollowResponseItem
import com.dicoding.fundamental.githubsearchapp.model.GithubSearchResponse
import com.dicoding.fundamental.githubsearchapp.model.GithubUser
import retrofit2.Call
import retrofit2.http.*

interface ApiService {

    @Headers("Authorization: token ${BuildConfig.TOKEN}")
    @GET("search/users")
    fun getUser(
        @Query("q") query: String?,
    ): Call<GithubSearchResponse>

    @Headers("Authorization: token ${BuildConfig.TOKEN}")
    @GET("users/{username}")
    fun getDetailUser(
        @Path("username")
        username: String?
    ): Call<GithubUser>

    @Headers("Authorization: token ${BuildConfig.TOKEN}")
    @GET("users/{username}/followers")
    fun getUserFollowers(
        @Path("username")
        username: String?
    ): Call<ArrayList<FollowResponseItem>>

    @Headers("Authorization: token ${BuildConfig.TOKEN}")
    @GET("users/{username}/following")
    fun getUserFollowing(
        @Path("username")
        username: String?
    ): Call<ArrayList<FollowResponseItem>>
}