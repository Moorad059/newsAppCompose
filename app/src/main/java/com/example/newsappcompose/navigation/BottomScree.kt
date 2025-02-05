package com.example.newsappcompose.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.ui.graphics.vector.ImageVector
import com.example.newsappcompose.R

sealed class BottomScree(
    val route: String,
    val title: String,
    val selectedIcon: Int,
    val unSelectedIcon: Int
) {
    data object Home :
        BottomScree("homeScreen", "Ana Sayfa", R.drawable.home_black, R.drawable.home_white)

    data object Saved : BottomScree("savedScreen", "Ayarlar", R.drawable.bookmark, R.drawable.save)
}