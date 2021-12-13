package com.merkost.github_api.model.entity.users

import com.google.gson.annotations.SerializedName


data class UsersSearchResponse (

	@SerializedName("total_count") val total_count : Int,
	@SerializedName("incomplete_results") val incomplete_results : Boolean,
	@SerializedName("items") val items : List<UsersSearchResult>
)