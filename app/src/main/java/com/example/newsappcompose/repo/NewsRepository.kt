package com.example.newsappcompose.repo

import com.example.newsappcompose.api.ApiService
import com.example.newsappcompose.base.BaseRepository
import javax.inject.Inject

class NewsRepository @Inject constructor(private val apiService: ApiService):BaseRepository(){
    suspend fun getTopNews()=safeApiRequest {
        apiService.getTopNews()

    }
}