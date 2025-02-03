package com.example.newsappcompose.base

import android.net.http.HttpException
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

abstract class BaseRepository {


    suspend fun <T> safeApiRequest(apiRequest: suspend () -> T): NetworkResponse<T> {
        return withContext(Dispatchers.IO) {
            try {
                NetworkResponse.Success(apiRequest.invoke())
            } catch (throwable: Throwable) {
              NetworkResponse.Error(throwable)
            }
        }
    }

}