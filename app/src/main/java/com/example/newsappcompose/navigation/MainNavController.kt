package com.example.newsappcompose.navigation

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.newsappcompose.Article
import com.example.newsappcompose.screen.detail.DetailScreen
import com.example.newsappcompose.screen.home.HomeScreen
import com.example.newsappcompose.screen.saved.SavedScreen
import com.google.gson.Gson

@Composable
fun MainNavController() {

    val navController = rememberNavController()

    Scaffold(bottomBar = {
        BottomNavBar(navController)
    }) { innerpading ->
        NavHost(
            modifier = Modifier.padding(innerpading),
            startDestination = BottomScree.Home.route,
            navController = navController
        ) {
            composable(route = BottomScree.Home.route) {
                HomeScreen(navController = navController)
            }

            composable(route = BottomScree.Saved.route) {
                SavedScreen()
            }

            composable(route = Screen.Detail.route) { backStackEntry ->
                val json = backStackEntry.arguments?.getString("modelJson")
                val gson = Gson()
                val model: Article? = gson.fromJson(json, Article::class.java)
                model?.let {
                    DetailScreen(it)
                }
            }
        }
    }

}

@Composable
fun BottomNavBar(navController: NavController) {
    val items = listOf(
        BottomScree.Home,
        BottomScree.Saved
    )

    NavigationBar {
        val currentRoute = navController.currentBackStackEntryAsState().value?.destination?.route

        items.forEach { screen ->
            NavigationBarItem(
                icon = {
                    Image(
                        painter = painterResource(if (currentRoute == screen.route) screen.selectedIcon else screen.unSelectedIcon),
                        contentDescription = screen.title,
                        modifier = Modifier.size(20.dp)
                    )
                },
                label = { Text(screen.title) },
                selected = currentRoute == screen.route,
                onClick = {
                    navController.navigate(screen.route) {
                        popUpTo(navController.graph.startDestinationId) { saveState = true }
                        launchSingleTop = true
                        restoreState = true
                    }
                }
            )
        }
    }
}