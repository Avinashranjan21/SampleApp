package com.example.sampleapp.login

import javax.inject.Inject

class UserRepository @Inject constructor() {
    private var isLoggedIn = false

    suspend fun isUserLoggedIn(): Boolean {
        return isLoggedIn
    }

    suspend fun login(username: String, password: String): Boolean {
        if (username.isNotEmpty() && password.isNotEmpty()) {
            isLoggedIn = true
            return true
        }
        return false
    }
}