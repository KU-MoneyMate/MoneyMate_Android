package com.moneymate.moneymate.data.dto.mypage.response

import kotlinx.serialization.Serializable

@Serializable
data class UserInfoResponse(
    val userId: String,
    val userName: String,
    val password: String?,
    val birthday: String,
    val phoneNumber: String
)

//받은 데이터 형식 변경
data class UserInfo(
    val userId: String,
    val name: String,
    val year: String,
    val month: String,
    val day: String,
    val phone1: String,
    val phone2: String,
    val phone3: String
) {
    companion object {
        fun fromResponse(response: UserInfoResponse): UserInfo {
            // 생년월일 분리 (yyyy-mm-dd)
            val parts = response.birthday.split("-")
            val year = parts.getOrElse(0) { "" }
            val month = parts.getOrElse(1) { "" }
            val day = parts.getOrElse(2) { "" }

            // 전화번호 분리 (000-0000-0000)
            val phoneParts = response.phoneNumber.split("-")
            val phone1 = phoneParts.getOrElse(0) { "" }
            val phone2 = phoneParts.getOrElse(1) { "" }
            val phone3 = phoneParts.getOrElse(2) { "" }

            return UserInfo(
                userId = response.userId,
                name = response.userName,
                year = year,
                month = month,
                day = day,
                phone1 = phone1,
                phone2 = phone2,
                phone3 = phone3
            )
        }
    }
}