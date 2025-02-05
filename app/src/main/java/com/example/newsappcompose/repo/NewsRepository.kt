package com.example.newsappcompose.repo

import com.example.newsappcompose.Article
import com.example.newsappcompose.api.ApiService
import com.example.newsappcompose.base.BaseRepository
import com.example.newsappcompose.room.ArticleDao
import javax.inject.Inject

class NewsRepository @Inject constructor(
    private val apiService: ApiService,
    private val articleDao: ArticleDao
) : BaseRepository() {
    suspend fun getTopNews(query: String) = safeApiRequest {
        apiService.getTopNews(query = query)
    }

    suspend fun insert(article: Article) {
        articleDao.insertArticle(article)
    }

    suspend fun getAllArticles(): List<Article> {
        return articleDao.getAllArticles()
    }

    suspend fun delete(article: Article) {
        articleDao.deleteArticle(article)
    }
}