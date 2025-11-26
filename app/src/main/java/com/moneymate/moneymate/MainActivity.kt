package com.moneymate.moneymate

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.getValue
import androidx.compose.material3.LocalContentColor
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.Box
import androidx.compose.ui.Alignment
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.lifecycle.lifecycleScope
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.moneymate.moneymate.ui.navigation.BottomNavItem
import com.moneymate.moneymate.ui.navigation.MoneyMateNavGraph
import com.moneymate.moneymate.ui.navigation.Route
import com.moneymate.moneymate.ui.theme.MoneyMateTheme
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    private var showSplash = true

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // Splash
        val splashScreen = installSplashScreen()
        splashScreen.setKeepOnScreenCondition {
            // 이 람다 안의 값이 true이면 Splash 화면이 유지됨
            showSplash
        }

        lifecycleScope.launch { // 2초후에 Splash 화면 종료
            delay(1500L)
            showSplash = false
        }

        enableEdgeToEdge()
        setContent {
            MoneyMateTheme {
                val navController = rememberNavController()
                val navBarItems = listOf(
                    BottomNavItem(
                        label = "자산 조회",
                        route = Route.Home.route,
                        icon = R.drawable.ic_bottomnav_home
                    ),
                    BottomNavItem(
                        label = "금융 정보",
                        route = Route.Finance.route,
                        icon = R.drawable.ic_bottomnav_finance
                    ),
                    BottomNavItem(
                        label = "인사이트",
                        route = Route.InsightMenu.route,
                        icon = R.drawable.ic_moneymatelogo
                    ),
                    BottomNavItem(
                        label = "자산 관리",
                        route = Route.Manage.route,
                        icon = R.drawable.ic_bottomnav_manage
                    ),
                    BottomNavItem(
                        label = "마이페이지",
                        route = Route.MyPage.route,
                        icon = R.drawable.ic_bottomnav_mypage
                    )
                )

                //BottomNavigation을 보여줄 화면들
                val bottomNavRoutes = listOf(
                    Route.Home.route,
                    Route.Finance.route,
                    Route.InsightMenu.route,
                    Route.Manage.route,
                    Route.MyPage.route
                )
                val navBackStackEntry by navController.currentBackStackEntryAsState()
                val currentRoute = navBackStackEntry?.destination?.route

                Scaffold(
                    modifier = Modifier.systemBarsPadding(),
                    bottomBar = {
                        if (currentRoute in bottomNavRoutes) {
                            NavigationBar(
                                modifier = Modifier
                                    .drawBehind {
                                        val strokeWidth = 1.dp.toPx()
                                        drawLine(
                                            color = Color(0xFFF5F5F5), // NavigationBar의 상단 테두리
                                            start = Offset(0f, 0f),
                                            end = Offset(size.width, 0f),
                                            strokeWidth = strokeWidth,
                                        )
                                    },
                                containerColor = Color.White
                            ) {
                                val navBackStackEntry by navController.currentBackStackEntryAsState()
                                val currentDestination = navBackStackEntry?.destination

                                navBarItems.forEach { item ->
                                    val selected =
                                        currentDestination?.hierarchy?.any { it.route == item.route } == true
                                    val isInsightMenu = item.route == Route.InsightMenu.route
                                    
                                    NavigationBarItem(
                                        icon = {
                                            Box(
                                                modifier = Modifier.size(38.dp),
                                                contentAlignment = Alignment.Center
                                            ) {
                                                Icon(
                                                    painter = painterResource(id = item.icon),
                                                    contentDescription = item.label,
                                                    tint = if (isInsightMenu) Color.Unspecified else LocalContentColor.current,
                                                    modifier = if (isInsightMenu) Modifier.size(38.dp) else Modifier.size(28.dp)
                                                )
                                            }
                                        },
                                        label = {
                                            Text(
                                                text = item.label,
                                            )
                                        },
                                        selected = selected,
                                        onClick = {
                                            navController.navigate(item.route) {
                                                // Home 화면까지 모든 백스택 제거 (Bottom Nav의 첫 화면)
                                                popUpTo(Route.Home.route) {
                                                    inclusive = false
                                                    saveState = true
                                                }
                                                // 같은 화면이 스택에 있으면 재사용
                                                launchSingleTop = true
                                                // 이전 상태 복원
                                                restoreState = true
                                            }
                                        },
                                        colors = if (isInsightMenu) {
                                            NavigationBarItemDefaults.colors(
                                                indicatorColor = Color.Transparent,
                                                selectedIconColor = Color.Unspecified,
                                                selectedTextColor = MoneyMateTheme.colors.deepBlue,
                                                unselectedIconColor = Color.Unspecified,
                                                unselectedTextColor = MoneyMateTheme.colors.lightGray
                                            )
                                        } else {
                                            NavigationBarItemDefaults.colors(
                                                indicatorColor = Color.Transparent,
                                                selectedIconColor = MoneyMateTheme.colors.deepBlue,
                                                selectedTextColor = MoneyMateTheme.colors.deepBlue,
                                                unselectedIconColor = MoneyMateTheme.colors.lightGray,
                                                unselectedTextColor = MoneyMateTheme.colors.lightGray
                                            )
                                        }
                                    )
                                }
                            }
                        }
                    }
                ) { innerPadding ->
                    MoneyMateNavGraph(
                        navController = navController,
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}