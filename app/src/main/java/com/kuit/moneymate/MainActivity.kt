package com.kuit.moneymate

import android.os.Bundle
import android.view.WindowInsets
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemColors
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.kuit.moneymate.ui.navigation.BottomNavItem
import com.kuit.moneymate.ui.navigation.MoneyMateNavGraph
import com.kuit.moneymate.ui.navigation.Route
import com.kuit.moneymate.ui.theme.MoneyMateTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
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

                Scaffold(
                    modifier = Modifier.systemBarsPadding(),
                    bottomBar = {
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
                                val selected = currentDestination?.hierarchy?.any { it.route == item.route } == true
                                NavigationBarItem(
                                    icon = {
                                        Icon(
                                            painter = painterResource(id = item.icon),
                                            contentDescription = item.label,
//                                            tint = if (selected) MoneyMateTheme.colors.deepBlue else MoneyMateTheme.colors.lightGray
                                        )
                                    },
                                    label = {
                                        Text(
                                            text = item.label,
//                                            color = if (selected) MoneyMateTheme.colors.deepBlue else MoneyMateTheme.colors.lightGray
                                        )
                                    },
                                    selected = selected,
                                    onClick = {
                                        navController.navigate(item.route) {
                                            popUpTo(navController.graph.findStartDestination().id) {
                                                saveState = true
                                            }
                                            launchSingleTop = true
                                            restoreState = true
                                        }
                                    },
                                    colors = NavigationBarItemDefaults.colors(
                                        indicatorColor = Color.Transparent,
                                        selectedIconColor = MoneyMateTheme.colors.deepBlue,
                                        selectedTextColor = MoneyMateTheme.colors.deepBlue,
                                        unselectedIconColor = MoneyMateTheme.colors.lightGray,
                                        unselectedTextColor = MoneyMateTheme.colors.lightGray
                                    )
                                )
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