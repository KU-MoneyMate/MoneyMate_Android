package com.moneymate.moneymate.ui.mypage

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.moneymate.moneymate.data.dto.mypage.request.UpdateUserRequest
import com.moneymate.moneymate.data.dto.mypage.request.VerifyPasswordRequest
import com.moneymate.moneymate.data.dto.mypage.response.UserInfo
import com.moneymate.moneymate.data.repository.MyPageRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asSharedFlow
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

    private val _showPasswordDialog = MutableStateFlow(false)
    val showPasswordDialog: StateFlow<Boolean> = _showPasswordDialog.asStateFlow()

    private val _verificationSuccessEvent = MutableSharedFlow<Unit>()
    val verificationSuccessEvent: SharedFlow<Unit> = _verificationSuccessEvent.asSharedFlow()

    private val _targetAction = MutableStateFlow<MyPageAction?>(null)
    val targetAction: StateFlow<MyPageAction?> = _targetAction.asStateFlow()

    private val _isPasswordError = MutableStateFlow(false)
    val isPasswordError: StateFlow<Boolean> = _isPasswordError.asStateFlow()

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

    //버튼 클릭시
    fun onPasswordResetClicked() {
        _targetAction.value = MyPageAction.RESET_PASSWORD
        _showPasswordDialog.value = true
    }
    fun onDeleteAccountClicked() {
        _targetAction.value = MyPageAction.DELETE_ACCOUNT
        _showPasswordDialog.value = true
    }

    //다이얼로그 종료
    fun onDialogDismissed() {
        _showPasswordDialog.value = false
        _isPasswordError.value = false
    }

    fun verifyCurrentPassword(password: String) {
        if (_isLoading.value) return

        _isPasswordError.value = false

        viewModelScope.launch {
            _isLoading.value = true
            myPageRepository.verifyCurrentPassword(password = password)
                .onSuccess {
                    Log.d("MyPageViewModel", "현재 비밀번호 검증 성공")
                    onDialogDismissed()

                    when (_targetAction.value) {
                        MyPageAction.RESET_PASSWORD -> _verificationSuccessEvent.emit(Unit)
                        // 💡 performAccountDeletion() 대신 deleteAccount() 호출
                        MyPageAction.DELETE_ACCOUNT -> deleteAccount()
                        else -> Log.w("MyPageViewModel", "No target action set.")
                    }
                    _targetAction.value = null
                }
                .onFailure { t ->
                    Log.e("MyPageViewModel", "비밀번호 검증 실패: ${t.message}")
                    _isPasswordError.value = true
                }
            _isLoading.value = false
        }
    }

    private fun deleteAccount() {
        // TODO: 회원 탈퇴 API 호출 (ViewModel에 MyPageRepository가 있으므로 여기서 처리 가능)
        viewModelScope.launch {
            // myPageRepository.deleteAccount()
            // onSuccess 시: onLogoutClick 등의 콜백 호출 또는 메인 화면 이동 이벤트 발생
        }
    }
}

enum class MyPageAction {
    RESET_PASSWORD, // 비밀번호 재설정 화면으로 이동
    DELETE_ACCOUNT  // 회원 탈퇴 로직 실행
}