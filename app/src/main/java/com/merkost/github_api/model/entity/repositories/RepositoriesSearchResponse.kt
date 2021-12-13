package com.merkost.github_api.model.entity.repositories

import com.google.gson.annotations.SerializedName

data class RepositoriesSearchResponse(
    @SerializedName("total_count")
    val totalCount: Int?,
    @SerializedName("items")
    val repositoriesSearchResults: List<RepositoriesSearchResult>?
)
