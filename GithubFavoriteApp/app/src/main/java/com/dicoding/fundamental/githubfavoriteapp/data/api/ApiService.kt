package com.dicoding.fundamental.githubfavoriteapp.data.api

import com.dicoding.fundamental.githubfavoriteapp.data.model.response.FollowResponseItem
import com.dicoding.fundamental.githubfavoriteapp.data.model.response.GithubSearchResponse
import com.dicoding.fundamental.githubfavoriteapp.data.model.response.GithubUser
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {

    @GET("search/users")
    fun getUser(
        @Query("q") query: String?
    ): Call<GithubSearchResponse>

    @GET("users/{username}")
    fun getDetailUser(
        @Path("username")
        username: String?
    ): Call<GithubUser>

    @GET("users/{username}/followers")
    fun getUserFollowers(
        @Path("username")
        username: String?
    ): Call<ArrayList<FollowResponseItem>>

    @GET("users/{username}/following")
    fun getUserFollowing(
        @Path("username")
        username: String?
    ): Call<ArrayList<FollowResponseItem>>
}