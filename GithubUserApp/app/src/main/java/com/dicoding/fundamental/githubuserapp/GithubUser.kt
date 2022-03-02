package com.dicoding.fundamental.githubuserapp

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class GithubUser(
    var name: String,
    var username: String,
    var avatar: Int,
    var following: String,
    var followers: String,
    var repository: String,
    var company: String,
    var location: String
) : Parcelable