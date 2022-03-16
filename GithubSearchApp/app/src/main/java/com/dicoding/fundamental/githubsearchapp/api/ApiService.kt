package com.dicoding.fundamental.githubsearchapp.api

import com.dicoding.fundamental.githubsearchapp.model.FollowResponseItem
import com.dicoding.fundamental.githubsearchapp.model.GithubSearchResponse
import com.dicoding.fundamental.githubsearchapp.model.GithubUser
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {

    @Headers("Authorization: token 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")
    @GET("search/users")
    fun getUser(
        @Query("q") query: String?,
    ): Call<GithubSearchResponse>

    @Headers("Authorization: token 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")
    @GET("users/{username}")
    fun getDetailUser(
        @Path("username")
        username: String?
    ): Call<GithubUser>

    @Headers("Authorization: token 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")
    @GET("users/{username}/followers")
    fun getUserFollowers(
        @Path("username")
        username: String?
    ): Call<ArrayList<FollowResponseItem>>

    @Headers("Authorization: token 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")
    @GET("users/{username}/following")
    fun getUserFollowing(
        @Path("username")
        username: String?
    ): Call<ArrayList<FollowResponseItem>>
}