package com.merkost.github_api.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.merkost.github_api.model.api.GithubRepository
import com.merkost.github_api.viewmodels.viewstates.BaseViewState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class MainViewModel(val repository: GithubRepository) : ViewModel() {

    private val _uiState = MutableStateFlow<BaseViewState>(BaseViewState.Success(null))
    val uiState: StateFlow<BaseViewState>
        get() = _uiState

    fun searchFor(keyword: String) {
        viewModelScope.launch {
            _uiState.value = BaseViewState.Loading()
            repository.searchGithubUsers(word = keyword).collect { searchResponse ->
                _uiState.value = BaseViewState.Success(searchResponse)
            }
        }
    }

}