package com.merkost.github_api.di

import com.merkost.github_api.model.api.GithubRepository
import com.merkost.github_api.model.repository.GitHubRepositoryImpl
import com.merkost.github_api.ui.MainActivity
import com.merkost.github_api.ui.MainFragment
import com.merkost.github_api.viewmodels.MainViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.qualifier.named
import org.koin.dsl.module


val appModule = module {
    single<GithubRepository> { GitHubRepositoryImpl() }
    //single { MainViewModel(get()) }
}

val mainActivity = module {
    scope(named<MainActivity>()) {
        scope<MainFragment> { viewModel { MainViewModel(get()) } }

    }


}