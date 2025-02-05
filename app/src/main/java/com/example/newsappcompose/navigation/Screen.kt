package com.example.newsappcompose.navigation

import android.net.Uri
import com.example.newsappcompose.Article
import com.google.gson.Gson

sealed class Screen(val route: String) {
    data object Detail : Screen("detailScreen/{modelJson}") {
        fun createRoute(article: Article): String {
            val gson = Gson()
            val json = gson.toJson(article)
            return "detailScreen/${Uri.encode(json)}"
        }
    }
}
