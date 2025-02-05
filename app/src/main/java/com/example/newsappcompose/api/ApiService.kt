package com.example.newsappcompose.api

import com.example.newsappcompose.NewsResponse
import com.example.newsappcompose.util.Constant.API_KEY
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Query

interface ApiService {

    @GET("top-headlines")
    @Headers("User-Agent: Mozilla/5.0")
    suspend fun getTopNews(
        @Query("country") country: String = "us",
        @Query("apiKey") apiKey: String = API_KEY,
        @Query("q") query: String
    ): Response<NewsResponse>


}