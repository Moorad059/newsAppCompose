package com.example.newsappcompose.screen.home

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.newsappcompose.Article
import com.example.newsappcompose.base.NetworkResponse
import com.example.newsappcompose.repo.NewsRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(private val repo: NewsRepository) : ViewModel() {

    private val _homeUIstate: MutableStateFlow<HomeUIState> = MutableStateFlow(HomeUIState())
    val homeUistate: StateFlow<HomeUIState> get() = _homeUIstate

    private fun getState() = _homeUIstate.value

    private var topList = mutableListOf<Article>()

    init {
        getNews()
    }

    fun getNews(query: String = "") {
        viewModelScope.launch {
            when (val reponse = repo.getTopNews(query)) {
                is NetworkResponse.Success -> {
                    reponse.data?.body().let {
                        if (topList.isEmpty()) topList = it?.articles.orEmpty().toMutableList()
                        _homeUIstate.value =
                            getState().copy(
                                isLoading = false,
                                newsList = it?.articles.orEmpty(),
                                topNews = topList
                            )
                    }
                }

                is NetworkResponse.Error -> {
                }
            }
        }

    }


}

data class HomeUIState(
    val isLoading: Boolean = false,
    val newsList: List<Article> = emptyList(),
    val topNews: List<Article> = emptyList()
)