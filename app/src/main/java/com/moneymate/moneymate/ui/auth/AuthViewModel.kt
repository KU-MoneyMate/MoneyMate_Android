package com.moneymate.moneymate.ui.auth

import android.view.View
import androidx.lifecycle.ViewModel
import com.moneymate.moneymate.data.service.AuthService
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class AuthViewModel @Inject constructor(
    private val authService: AuthService
): ViewModel() {

}