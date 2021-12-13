package com.merkost.github_api.model.repository

import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import com.merkost.github_api.model.api.GithubRepository
import com.merkost.github_api.model.entity.users.UsersSearchResponse
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import okhttp3.OkHttpClient

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class GitHubRepositoryImpl() : GithubRepository {

    companion object {
        const val BASE_URL = "https://api.github.com"
    }

    override suspend fun searchGithubRepositories(word: String) = flow {
        val response = getService().searchGithubRepositories(word)
        emit(response)
    }

    override suspend fun searchGithubUsers(word: String) = flow {
        val response = getService().searchGithubUsers(word)
        emit(response)
    }

    private fun getService(): GitHubApi {
        return createRetrofit().create(GitHubApi::class.java)
    }

    private fun createRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(CoroutineCallAdapterFactory())
            .client(createOkHttpClient())
            .build()
    }

    private fun createOkHttpClient(): OkHttpClient {
        return OkHttpClient.Builder().build()
    }

}
