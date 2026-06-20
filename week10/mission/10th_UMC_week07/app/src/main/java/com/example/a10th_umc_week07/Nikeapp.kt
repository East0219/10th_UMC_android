package com.example.a10th_umc_week07

import android.graphics.drawable.VectorDrawable
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.a10th_umc_week07.BasketScreen
import com.example.a10th_umc_week07.BuyScreen
import com.example.a10th_umc_week07.HomeScreen
import com.example.a10th_umc_week07.ProfileScreen
import com.example.a10th_umc_week07.WishlistScreen
import java.util.Vector

sealed class Screen(
    val route: String,
    val label: String,
    val iconRes: Int
) {
    object Home     : Screen("home",      "홈",       R.drawable.ic_housesimple)
    object Buy      : Screen("buy",       "구매하기",  R.drawable.ic_listmagnifyingglass)
    object Wishlist : Screen("wishlist",  "위시리스트", R.drawable.ic_heartstraight)
    object Basket   : Screen("basket",    "장바구니",  R.drawable.ic_bagsimple)
    object Profile  : Screen("profile",   "프로필",   R.drawable.ic_user)
}

private val bottomNavItems = listOf(
    Screen.Home,
    Screen.Buy,
    Screen.Wishlist,
    Screen.Basket,
    Screen.Profile
)

@Composable
fun NikeApp() {
    val navController = rememberNavController()
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentDestination = navBackStackEntry?.destination

    val showBottomBar = bottomNavItems.any { it.route == currentDestination?.route }

    Scaffold(
        bottomBar = {
            if (showBottomBar) {
                NavigationBar(
                    containerColor = Color.White
                ) {
                    bottomNavItems.forEach { screen ->
                        val selected = currentDestination
                            ?.hierarchy
                            ?.any { it.route == screen.route } == true

                        NavigationBarItem(
                            selected = selected,
                            onClick = {
                                navController.navigate(screen.route) {
                                    popUpTo(navController.graph.findStartDestination().id) {
                                        saveState = true
                                    }
                                    launchSingleTop = true
                                    restoreState = true
                                }
                            },
                            icon = {
                                Icon(
                                    painter = painterResource(id = screen.iconRes),
                                    contentDescription = screen.label
                                )
                            },
                            label = { Text(screen.label) },
                            colors = NavigationBarItemDefaults.colors(
                                selectedIconColor = Color.Black,
                                selectedTextColor = Color.Black,
                                unselectedIconColor = Color.Gray,
                                unselectedTextColor = Color.Gray,
                                indicatorColor = Color.Transparent
                            )
                        )
                    }
                }
            }
        }
    ) { innerPadding ->
        NavHost(
            navController = navController,
            startDestination = Screen.Home.route,
            modifier = Modifier.padding(innerPadding)
        ) {
            composable(Screen.Home.route) {
                HomeScreen(
                    onProductClick = { navController.navigate(Screen.Buy.route) }
                )
            }
            composable(Screen.Buy.route) {
                BuyScreen(onBackClick = { navController.popBackStack() })
            }
            composable(Screen.Wishlist.route) {
                WishlistScreen(
                    onProductClick = { navController.navigate(Screen.Buy.route) }
                )
            }
            composable(Screen.Basket.route) {
                BasketScreen(
                    onBuyClick = { navController.navigate(Screen.Buy.route) }
                )
            }
            composable(Screen.Profile.route) {
                ProfileScreen()
            }
        }
    }
}