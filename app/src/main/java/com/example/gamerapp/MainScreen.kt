package com.example.gamerapp

import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.*
import com.example.gamerapp.NewsScreen
import com.example.gamerapp.ProfileScreen
import com.example.gamerapp.StoreScreen

data class BottomNavItem(val label: String, val icon: androidx.compose.ui.graphics.vector.ImageVector, val route: String)

@Composable
fun MainScreen() {
    val navController = rememberNavController()
    val items = listOf(
        BottomNavItem("News", Icons.Default.Info, "news"),
        BottomNavItem("Store", Icons.Default.ShoppingCart, "store"),
        BottomNavItem("Profile", Icons.Default.AccountCircle, "profile")
    )

    Scaffold(
        bottomBar = { BottomNavBar(navController, items) }
    ) { innerPadding ->
        NavHost(
            navController = navController,
            startDestination = "news", // NewsScreen par défaut
            modifier = Modifier.padding(innerPadding)
        ) {
            composable("news") { NewsScreen() }
            composable("store") { StoreScreen() }
            composable("profile") { ProfileScreen() }
        }
    }
}

@Composable
fun BottomNavBar(navController: NavHostController, items: List<BottomNavItem>) {
    NavigationBar {
        val navBackStackEntry by navController.currentBackStackEntryAsState()
        val currentRoute = navBackStackEntry?.destination?.route
        items.forEach { item ->
            NavigationBarItem(
                selected = currentRoute == item.route,
                onClick = { navController.navigate(item.route) },
                label = { Text(item.label) },
                icon = { Icon(item.icon, contentDescription = item.label) }
            )
        }
    }
}
