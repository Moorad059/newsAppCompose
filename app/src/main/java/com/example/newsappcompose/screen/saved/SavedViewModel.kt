package com.example.newsappcompose.screen.saved

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.newsappcompose.Article
import com.example.newsappcompose.repo.NewsRepository
import com.example.newsappcompose.screen.home.HomeUIState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SavedViewModel @Inject constructor(
    private val newsRepository: NewsRepository
) : ViewModel() {

    private val _savedUIstate: MutableStateFlow<SavedUiState> = MutableStateFlow(SavedUiState())
    val savedUistate: StateFlow<SavedUiState> get() = _savedUIstate

    private fun getState() = _savedUIstate.value

    fun getSaved() {
        viewModelScope.launch {
            val list = newsRepository.getAllArticles()
            _savedUIstate.value =
                getState().copy(
                    newsList = list
                )
        }
    }
}

data class SavedUiState(
    val newsList: List<Article> = emptyList(),
)