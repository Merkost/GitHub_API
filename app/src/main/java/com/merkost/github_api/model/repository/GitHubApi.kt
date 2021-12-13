package com.merkost.github_api.model.repository

import com.merkost.github_api.model.entity.repositories.RepositoriesSearchResponse
import com.merkost.github_api.model.entity.users.UsersSearchResponse
import retrofit2.http.GET
import retrofit2.http.Query

//Docs: https://developer.github.com/v3/search/

interface GitHubApi {

    //@Headers("Accept: application/vnd.github.mercy-preview+json")
    @GET("search/repositories")
    suspend fun searchGithubRepositories(@Query("q") term: String?): RepositoriesSearchResponse

    @GET("search/users")
    suspend fun searchGithubUsers(@Query("q") term: String?): UsersSearchResponse

}
