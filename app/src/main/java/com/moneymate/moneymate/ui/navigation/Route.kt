package com.moneymate.moneymate.ui.navigation

sealed class Route(val route: String){
    // 자산 조회(홈)
    object Home: Route(route = "home")

    // 금융 정보
    object Finance: Route(route = "finance")

    // 자산 관리
    object Manage: Route(route = "manage")

    // 마이페이지
    object MyPage: Route(route = "myPage")
}