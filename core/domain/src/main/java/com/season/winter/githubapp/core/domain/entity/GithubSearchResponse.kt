package com.season.winter.githubapp.core.domain.entity

import com.google.gson.annotations.SerializedName

data class GithubSearchResponse(
    @field:SerializedName("total_count") val totalCount: Int,
    @field:SerializedName("incomplete_results") val incompleteResults: Boolean,
    @field:SerializedName("items") val users: List<GithubUserEntity>,
)

//     "total_count": 5,
//    "incomplete_results": false,
//    "items": [