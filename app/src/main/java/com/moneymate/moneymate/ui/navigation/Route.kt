package com.moneymate.moneymate.ui.navigation

sealed class Route(val route: String){
    // 자산 조회(홈)
    object Home: Route(route = "home")
    object AddAccount: Route(route = "home/addAccount")
    object AddAsset: Route(route = "home/addAsset")
    object TransactionHistory: Route(route = "home/transactionHistory")

    // 금융 정보
    object Finance: Route(route = "finance")

    // 자산 관리
    object Manage: Route(route = "manage")
    object RetireGraph : Route(route = "retire_graph")
    object RetireInput : Route(route = "myPage/retireInput")
    object RetireResult : Route(route = "myPage/retireResult")

    // 마이페이지
    object MyPage: Route(route = "myPage")

}