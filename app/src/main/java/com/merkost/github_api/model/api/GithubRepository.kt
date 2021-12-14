package com.merkost.github_api.model.api

import com.merkost.github_api.model.entity.repositories.RepositoriesSearchResponse
import com.merkost.github_api.model.entity.user_repos.UserReposItem
import com.merkost.github_api.model.entity.user_repos.UserReposResponse
import com.merkost.github_api.model.entity.users.UsersSearchResponse
import kotlinx.coroutines.flow.Flow

interface GithubRepository {
    suspend fun searchGithubRepositories(word: String): Flow<RepositoriesSearchResponse>
    suspend fun searchGithubUsers(word: String): Flow<UsersSearchResponse>
    suspend fun searchUserRepos(login: String): Flow<List<UserReposItem>>
}