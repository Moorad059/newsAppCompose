package com.example.newsappcompose.base

sealed class NetworkResponse <T>{
    data class Success<T>(val data:T?):NetworkResponse<T>()
    data class Error<Nothing>( val message:Throwable?):NetworkResponse<Nothing>()
}