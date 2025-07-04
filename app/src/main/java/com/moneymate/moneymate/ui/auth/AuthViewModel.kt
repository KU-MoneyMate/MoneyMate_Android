package com.moneymate.moneymate.ui.auth

import android.util.Log
import android.view.View
import android.view.WindowId
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.moneymate.moneymate.data.repository.AuthRepository
import com.moneymate.moneymate.data.service.AuthService
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AuthViewModel @Inject constructor(
    private val authRepository: AuthRepository
): ViewModel() {
    fun registerUser(
        userId: String,
        userName: String,
        password: String,
        phoneNumber: String,
        birthday: String
    ){
        viewModelScope.launch {
            authRepository.registerUser(
                userId = userId,
                userName = userName,
                password = password,
                phoneNumber = phoneNumber,
                birthday = birthday
            ).onSuccess { response ->
                Log.d("AuthViewModel", "회원가입) 성공:${response}")
            }.onFailure { response ->
                Log.d("AuthViewModel", "회원가입) 실패:${response.message.toString()}")
            }
        }
    }
}