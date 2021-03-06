package com.merkost.github_api.viewmodels.viewstates

sealed class BaseViewState {
    class Success<T>(val data: T) : BaseViewState()
    class Error(val error: Throwable) : BaseViewState()
    class Loading(val progress: Int? = null) : BaseViewState()
}