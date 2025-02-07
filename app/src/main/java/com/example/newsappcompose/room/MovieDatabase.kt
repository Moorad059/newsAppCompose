package com.example.newsappcompose.room

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.newsappcompose.Article

@Database(
    entities = [Article::class],
    version = 2,
    exportSchema = false
)
abstract class ArticleDataBase : RoomDatabase() {
    abstract fun articleDao(): ArticleDao
}