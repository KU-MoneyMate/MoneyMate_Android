package com.moneymate.moneymate.ui.auth

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.moneymate.moneymate.data.repository.AuthRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AuthViewModel @Inject constructor(
    private val authRepository: AuthRepository
) : ViewModel() {
    // 회원가입 입력 정보들
    private val _signupUserId = MutableStateFlow("")
    val signupUserId: StateFlow<String> = _signupUserId.asStateFlow()

    // ID 중복 확인 상태
    private val _idCheckStatus = MutableStateFlow<String?>(null)
    val idCheckStatus: StateFlow<String?> = _idCheckStatus.asStateFlow()

    private val _signupPassword = MutableStateFlow("")
    val signupPassword: StateFlow<String> = _signupPassword.asStateFlow()

    private val _signupName = MutableStateFlow("")
    val signupName: StateFlow<String> = _signupName.asStateFlow()

    private val _signupPhone = MutableStateFlow("")
    val signupPhone: StateFlow<String> = _signupPhone.asStateFlow()

    private val _signupBirthday = MutableStateFlow("")
    val signupBirthday: StateFlow<String> = _signupBirthday.asStateFlow()

    fun saveSignupId(userId: String, onSaveSuccess: () -> Unit) {
        _signupUserId.value = userId
        Log.d("AuthViewModel", "회원가입 아이디 저장: ${signupUserId.value}")
        onSaveSuccess()
    }

    fun saveSignupPassword(password: String, onSaveSuccess: () -> Unit) {
        _signupPassword.value = password
        Log.d("AuthViewModel", "회원가입 비밀번호 저장: ${signupPassword.value}")
        onSaveSuccess()
    }

    fun saveSignupName(name: String, onSaveSuccess: () -> Unit) {
        _signupName.value = name
        Log.d("AuthViewModel", "회원가입 이름 저장: ${signupName.value}")
        onSaveSuccess()
    }

    fun saveSignupPhone(phone: String, onSaveSuccess: () -> Unit) {
        _signupPhone.value = phone
        Log.d("AuthViewModel", "회원가입 전화번호 저장: ${signupPhone.value}")
        onSaveSuccess()
    }

    fun saveSignupBirthday(birthday: String, onSaveSuccess: () -> Unit) {
        _signupBirthday.value = birthday
        Log.d("AuthViewModel", "회원가입 생일 저장: ${signupBirthday.value}")
        onSaveSuccess()
    }

    fun clearIdCheckStatus() {
        _idCheckStatus.value = null
    }

    // 회원가입
    fun registerUser(onSignupSuccess: () -> Unit) {
        viewModelScope.launch {
            authRepository.registerUser(
                userId = _signupUserId.value,
                userName = _signupName.value,
                password = _signupPassword.value,
                phoneNumber = _signupPhone.value,
                birthday = _signupBirthday.value
            ).onSuccess { response ->
                Log.d("AuthViewModel", "회원가입 성공:${response}")
                onSignupSuccess()
            }.onFailure { response ->
                Log.d("AuthViewModel", "회원가입 실패:${response.message.toString()}")
            }
        }
    }

    // 로그인
    fun login(
        userId: String,
        password: String,
        onLoginSuccess: () -> Unit
    ) {
        viewModelScope.launch {
            authRepository.login(
                userId = userId,
                password = password
            ).onSuccess {
                Log.d("AuthViewModel", "로그인 성공")
                onLoginSuccess()
            }.onFailure {
                Log.d("AuthViewModel", "로그인 실패: ${it.message.toString()}")
            }
        }
    }

    // ID 중복 확인
    fun checkUserId(
        userId: String,
    ) {
        viewModelScope.launch {
            authRepository.checkUserId(userId)
                .onSuccess { response ->
                    _idCheckStatus.value = response.status
                    Log.d(
                        "AuthViewModel",
                        "Id 중복 확인 성공: ${response.status}"
                    )
                }.onFailure {
                    _idCheckStatus.value = "ERROR"
                    Log.d("AuthViewModel", "ID 중복 확인 실패: ${it.message.toString()}")
                }
        }
    }

    // sms 인증 요청
    fun requestPhoneVerification(
        phoneNumber: String,
    ) {
        viewModelScope.launch {
            authRepository.requestPhoneVerification(phoneNumber)
                .onSuccess { response ->
                    Log.d("AuthViewModel", "SMS 인증 요청 성공: ${response.message}")
                }.onFailure {
                    Log.d("AuthViewModel", "SMS 인증 요청 실패: ${it.message.toString()}")
                }
        }
    }

}