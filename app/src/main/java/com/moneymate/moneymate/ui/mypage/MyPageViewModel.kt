package com.moneymate.moneymate.ui.mypage

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.moneymate.moneymate.data.dto.mypage.request.UpdateUserRequest
import com.moneymate.moneymate.data.dto.mypage.request.VerifyPasswordRequest
import com.moneymate.moneymate.data.dto.mypage.response.UserInfo
import com.moneymate.moneymate.data.repository.MyPageRepository
import com.moneymate.moneymate.ui.mypage.component.MypageDialogType
import com.moneymate.moneymate.util.auth.isValidPassword
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

    private val _showConfirmDialog = MutableStateFlow<MypageDialogType?>(null)
    val showConfirmDialog: StateFlow<MypageDialogType?> = _showConfirmDialog.asStateFlow()

    private val _updateUserSuccessEvent = MutableSharedFlow<Unit>()
    val updateUserSuccessEvent = _updateUserSuccessEvent.asSharedFlow()

    private val _verificationSuccessEvent = MutableSharedFlow<Unit>()
    val verificationSuccessEvent: SharedFlow<Unit> = _verificationSuccessEvent.asSharedFlow()

    private val _logoutEvent = MutableSharedFlow<Unit>()
    val logoutEvent: SharedFlow<Unit> = _logoutEvent.asSharedFlow()

    private val _targetAction = MutableStateFlow<MyPageAction?>(null)
    val targetAction: StateFlow<MyPageAction?> = _targetAction.asStateFlow()

    private val _isPasswordError = MutableStateFlow(false)
    val isPasswordError: StateFlow<Boolean> = _isPasswordError.asStateFlow()

    private val _newPassword = MutableStateFlow("")
    val newPassword = _newPassword.asStateFlow()

    private val _confirmNewPassword = MutableStateFlow("")
    val confirmNewPassword = _confirmNewPassword.asStateFlow()

    private val _changePasswordSuccessEvent = MutableSharedFlow<Unit>()
    val changePasswordSuccessEvent = _changePasswordSuccessEvent.asSharedFlow()

    private val _errorMessage = MutableSharedFlow<String>()
    val errorMessage = _errorMessage.asSharedFlow()

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
                    _updateUserSuccessEvent.emit(Unit)
                }
                .onFailure { t ->
                    Log.e("MyPageViewModel", "사용자 정보 수정 실패: ${t.message}")
                    _errorMessage.emit("정보 수정에 실패했습니다.")
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

    fun onLogoutClicked() {
        _showConfirmDialog.value = MypageDialogType.CONFIRM_LOGOUT
    }

    //다이얼로그 종료
    fun onPasswordDialogDismissed() {
        _showPasswordDialog.value = false
        _isPasswordError.value = false
    }
    fun onConfirmDialogDismissed() {
        _showConfirmDialog.value = null
    }

    fun onConfirmDialog() {
        viewModelScope.launch {
            when (_showConfirmDialog.value) {
                MypageDialogType.CONFIRM_LOGOUT -> {
                    _logoutEvent.emit(Unit)
                }
                MypageDialogType.CONFIRM_DELETE_ACCOUNT -> {
                    deleteAccount()
                }
                else -> {}
            }
            _showConfirmDialog.value = null // 다이얼로그 닫기
        }
    }

    fun verifyCurrentPassword(password: String) {
        if (_isLoading.value) return

        _isPasswordError.value = false

        viewModelScope.launch {
            _isLoading.value = true
            myPageRepository.verifyCurrentPassword(password = password)
                .onSuccess {
                    Log.d("MyPageViewModel", "현재 비밀번호 검증 성공")
                    onPasswordDialogDismissed()

                    when (_targetAction.value) {
                        MyPageAction.RESET_PASSWORD -> _verificationSuccessEvent.emit(Unit)
                        MyPageAction.DELETE_ACCOUNT -> {
                            _showConfirmDialog.value = MypageDialogType.CONFIRM_DELETE_ACCOUNT
                        }
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
        viewModelScope.launch {
            _isLoading.value = true
            myPageRepository.deleteAccount()
                .onSuccess {
                    Log.d("MyPageViewModel", "회원 탈퇴 성공")
                    _logoutEvent.emit(Unit)
                }
                .onFailure { t ->
                    Log.e("MyPageViewModel", "회원 탈퇴 실패: ${t.message}")
                }
            _isLoading.value = false
        }
    }

    fun updateNewPassword(password: String) {
        _newPassword.value = password
    }

    fun updateConfirmNewPassword(password: String) {
        _confirmNewPassword.value = password
    }

    fun changePassword() {
        viewModelScope.launch {
            val newPw = _newPassword.value
            val confirmPw = _confirmNewPassword.value

            if (!newPw.isValidPassword()) {
                _errorMessage.emit("영문, 숫자를 포함하여 8자 이상 입력해주세요")
                return@launch
            }
            if (newPw != confirmPw) {
                _errorMessage.emit("비밀번호가 일치하지 않습니다")
                return@launch
            }

            _isLoading.value = true
            myPageRepository.changePassword(newPw)
                .onSuccess {
                    Log.d("MyPageViewModel", "비밀번호 변경 성공")
                    _changePasswordSuccessEvent.emit(Unit)
                }
                .onFailure { t ->
                    Log.e("MyPageViewModel", "비밀번호 변경 실패: ${t.message}")
                    _errorMessage.emit("비밀번호 변경에 실패했습니다.")
                }
            _isLoading.value = false
        }
    }
}

enum class MyPageAction {
    RESET_PASSWORD, // 비밀번호 재설정 화면으로 이동
    DELETE_ACCOUNT  // 회원 탈퇴 로직 실행
}