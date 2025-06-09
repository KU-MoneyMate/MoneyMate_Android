package com.moneymate.moneymate.util.auth

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.runBlocking
import okhttp3.Interceptor
import okhttp3.Response
import javax.inject.Inject

class AuthInterceptor @Inject constructor(
    private val tokenManager: TokenManager
) : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request().newBuilder()
        val accessToken = "Bearer eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJhYmNkIiwidWlkIjoiNjYzNDM3NjEtNjMzMS0zMDYyLTJkMzUtMzg2MzYzMmQzNDMzIiwiZXhwIjoxNzQ4OTczMTU1fQ.JEkRb8mkQVRqaqEIOJV6MwAmqjsZzsgbVPYc36lo9fE"
/*
        val accessToken =
            runBlocking { tokenManager.getAccessToken().flowOn(Dispatchers.IO).first() }
*/

        accessToken?.let {
            // header에 토큰을 추가
            request
                .addHeader("Authorization", "Bearer $it")
        }
        return chain.proceed(request.build())
    }
}
