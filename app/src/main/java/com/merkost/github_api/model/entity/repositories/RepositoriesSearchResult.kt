package com.merkost.github_api.model.entity.repositories

import com.google.gson.annotations.SerializedName

data class RepositoriesSearchResult(
    @SerializedName("id")
    val id: Int?,
    @SerializedName("name")
    val name: String?,
    @SerializedName("full_name")
    val fullName: String?,
    @SerializedName("private")
    private val private: Boolean?,
    @SerializedName("description")
    val description: String?,
    @SerializedName("updated_at")
    val updatedAt: String?,
    @SerializedName("size")
    val size: Int?,
    @SerializedName("stargazers_count")
    val stargazersCount: Int?,
    @SerializedName("language")
    val language: String?,
    @SerializedName("has_wiki")
    val hasWiki: Boolean?,
    @SerializedName("archived")
    val archived: Boolean?,
    @SerializedName("score")
    val score: Double?
)
