package com.dicoding.fundamental.mysharedpreferences

data class UserModel (
    var name: String? = null,
    var email: String? = null,
    var age: Int = 0,
    var phoneNumber: String? = null,
    var isLove: Boolean = false
)
