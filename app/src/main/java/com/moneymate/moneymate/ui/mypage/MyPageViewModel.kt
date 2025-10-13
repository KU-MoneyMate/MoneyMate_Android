package com.moneymate.moneymate.ui.mypage

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.moneymate.moneymate.data.dto.mypage.request.UpdateUserRequest
import com.moneymate.moneymate.data.dto.mypage.response.UserInfo
import com.moneymate.moneymate.data.repository.MyPageRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MyPageViewModel @Inject constructor(
    private val myPageRepository: MyPageRepository
) : ViewModel() {

    private val _userInfo = MutableStateFlow(
        UserInfo(
            userId = "",
            name = "",
            year = "",
            month = "",
            day = "",
            phone1 = "",
            phone2 = "",
            phone3 = ""
        )
    )
    val userInfo: StateFlow<UserInfo> = _userInfo.asStateFlow()

    private val _isLoading = MutableStateFlow(false)
    val isLoading: StateFlow<Boolean> = _isLoading.asStateFlow()

    init {
        fetchUserInfo()
    }

    // 사용자 정보 조회
    fun fetchUserInfo() {
        viewModelScope.launch {
            _isLoading.value = true
            myPageRepository.getUserInfo()
                .onSuccess { response ->
                    val newUserInfo = UserInfo.Companion.fromResponse(response)
                    _userInfo.value = newUserInfo
                    Log.d("MyPageViewModel", "사용자 정보 조회 성공")
                }
                .onFailure {
                    //
                }
            _isLoading.value = false
        }
    }

    fun updateName(name: String) { _userInfo.update { it.copy(name = name) } }
    fun updateId(userId: String) { _userInfo.update { it.copy(userId = userId) } }
    fun updateYear(year: String) { _userInfo.update { it.copy(year = year) } }
    fun updateMonth(month: String) { _userInfo.update { it.copy(month = month) } }
    fun updateDay(day: String) { _userInfo.update { it.copy(day = day) } }
    fun updatePhone1(p1: String) { _userInfo.update { it.copy(phone1 = p1) } }
    fun updatePhone2(p2: String) { _userInfo.update { it.copy(phone2 = p2) } }
    fun updatePhone3(p3: String) { _userInfo.update { it.copy(phone3 = p3) } }

    //사용자 정보 수정
    fun updateUserInfo() {
        if (_isLoading.value) return

        val request = _userInfo.value.toRequest() // 현재 UI State를 API Request DTO로 변환

        viewModelScope.launch {
            _isLoading.value = true
            myPageRepository.updateUserInfo(request)
                .onSuccess {
                    Log.d("MyPageViewModel", "사용자 정보 수정 성공")
                    // TODO: 성공 메시지 토스트 표시 또는 화면 이동 이벤트 추가
                }
                .onFailure { t ->
                    Log.e("MyPageViewModel", "사용자 정보 수정 실패: ${t.message}")
                    // TODO: 오류 메시지 사용자에게 표시
                }
            _isLoading.value = false
        }
    }

    // 데이터 포맷팅
    private fun UserInfo.toRequest(): UpdateUserRequest {
        val phoneNumber = "${phone1}-${phone2}-${phone3}"

        val formattedMonth = month.padStart(2, '0')
        val formattedDay = day.padStart(2, '0')
        val birthday = "$year-$formattedMonth-$formattedDay"

        return UpdateUserRequest(
            userId = this.userId,
            userName = this.name,
            phoneNumber = phoneNumber,
            birthday = birthday,
            password = null
        )
    }
}