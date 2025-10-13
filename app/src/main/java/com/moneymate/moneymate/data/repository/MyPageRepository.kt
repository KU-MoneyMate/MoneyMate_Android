package com.moneymate.moneymate.data.repository

import android.util.Log
import com.moneymate.moneymate.data.dto.mypage.request.UpdateUserRequest
import com.moneymate.moneymate.data.dto.mypage.request.VerifyPasswordRequest
import com.moneymate.moneymate.data.dto.mypage.response.UserInfoResponse
import com.moneymate.moneymate.data.service.MyPageService

class MyPageRepository (
    private val myPageService: MyPageService
) {
    //사용자 정보 조회
    suspend fun getUserInfo(): Result<UserInfoResponse> = runCatching {
        val response = myPageService.getUserInfo()
        Log.d("UserRepository", "사용자 정보 조회 성공: $response")
        response
    }.onFailure {
        Log.e("UserRepository", "사용자 정보 조회 실패: ${it.message}")
        throw it
    }

    //사용자 정보 수정
    suspend fun updateUserInfo(request: UpdateUserRequest) = runCatching {
        myPageService.updateUserInfo(request)
    }

    //비밀번호 확인
    suspend fun verifyCurrentPassword(password: String) = runCatching {
        val request = VerifyPasswordRequest(password = password)
        val response = myPageService.verifyPassword(request)

        if (response.isSuccessful) {
            Unit // 200 OK
        } else {
            val errorBody = response.errorBody()?.string() ?: "알 수 없는 오류"
            throw Exception("API 호출 실패: HTTP ${response.code()}, Error: $errorBody")
        }
    }
}
