package com.dicoding.fundamental.githubfavoriteapp.data.model.entity

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize


@Entity(tableName = "GithubUser")
@Parcelize
data class UserEntity(
    @PrimaryKey(autoGenerate = false)
    @ColumnInfo(name = "login")
    val username: String,

    @ColumnInfo(name = "avatar_url")
    val avatar: String? = null,

    @ColumnInfo(name= "favorite")
    var isFavorited: Boolean? = null
) : Parcelable