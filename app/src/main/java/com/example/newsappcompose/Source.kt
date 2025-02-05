package com.example.newsappcompose


import com.google.gson.annotations.SerializedName



data class Source(
    @SerializedName("id")
    val idSource: String?,
    @SerializedName("name")
    val name: String?
)