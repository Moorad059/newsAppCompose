package com.example.newsappcompose.screen.detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.newsappcompose.Article
import com.example.newsappcompose.repo.NewsRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailViewModel @Inject constructor(
    private val newsRepository: NewsRepository
) : ViewModel() {

    fun addSaved(article: Article) {
        viewModelScope.launch {
            newsRepository.insert(article)
        }
    }

}