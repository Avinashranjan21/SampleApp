package com.example.sampleapp.login

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val userRepository: UserRepository
) : ViewModel() {

    private val _isUserLoggedIn = MutableStateFlow<Boolean?>(null)
    val isUserLoggedIn: StateFlow<Boolean?> = _isUserLoggedIn

    fun checkLoginStatus() {
        viewModelScope.launch {
            _isUserLoggedIn.value = userRepository.isUserLoggedIn()
        }
    }

    fun loginUser(username: String, password: String, onSuccess: () -> Unit) {
        viewModelScope.launch {
            if (userRepository.login(username, password)) {
                onSuccess()
            }
        }
    }
}