package com.example.newsappcompose.screen.detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.newsappcompose.Article
import com.example.newsappcompose.repo.NewsRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailViewModel @Inject constructor(
    private val newsRepository: NewsRepository
) : ViewModel() {

    private val _detailUiState: MutableStateFlow<DetailUiState> = MutableStateFlow(DetailUiState())
    val detailUiState: StateFlow<DetailUiState> get() = _detailUiState

    private fun getState() = _detailUiState.value

    fun getDetail(article: Article) {
        viewModelScope.launch {
            val articleList = newsRepository.getAllArticles()

            val saved = articleList.any { it.url == article.url }

            val news = article.copy(isSaved = saved)

            _detailUiState.value = DetailUiState(news)
        }
    }

    fun addSaved(article: Article) {
        viewModelScope.launch {
            if (article.isSaved) {
                newsRepository.delete(article)
            } else {
                newsRepository.insert(article)
            }

            getState().article?.let {
                getDetail(it)
            }
        }
    }

}


data class DetailUiState(
    val article: Article? = null
)