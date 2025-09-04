package com.moneymate.moneymate.ui.navigation

sealed class Route(val route: String){
    //로그인, 회원가입
    object AuthGraph: Route(route = "authGraph")
    object Login : Route(route = "login")
    object SignUpID : Route(route = "signUpID")
    object SignUpPW : Route(route = "signUpPW")
    object SignUpName : Route(route = "signUpName")
    object SignUpPhone : Route(route = "signUpPhone")
    object SignUpVerification : Route(route = "signUpVerification")

    // 자산 조회(홈)
    object Home: Route(route = "home")
    object AddAccount: Route(route = "home/addAccount")
    object AddAsset: Route(route = "home/addAsset")
    object TransactionHistory: Route(route = "home/transactionHistory")
    object StockHolding : Route(route = "home/stockHolding")

    // 금융 정보
    object Finance: Route(route = "finance")
    object News : Route(route = "news")
    object NewsPublisherHome : Route(route = "news/publisher")
    object NewsArticle : Route(route = "news/newsArticle")

    // 자산 관리
    object Manage: Route(route = "manage")
    object RetireGraph : Route(route = "retireGraph")
    object RetireInput : Route(route = "retireGraph/input")
    object RetireResult : Route(route = "retireGraph/result")
    object PeerAssetStatistics: Route(route = "myPage/peerAssetStatistics")
    object AssetStatistics : Route(route = "myPage/assetStatistics")
    object SpendingStatistics : Route(route = "myPage/spendingStatistics")

    // 마이페이지
    object MyPage: Route(route = "myPage")

}