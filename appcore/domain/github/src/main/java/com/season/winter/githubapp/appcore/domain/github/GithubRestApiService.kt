package com.season.winter.githubapp.appcore.domain.github

import com.season.winter.githubapp.appcore.domain.github.entity.GithubSearchResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface GithubRestApiService {

    @GET("search/users")
    suspend fun searchUser(
        @Query("q") query: String,
        @Query("per_page") resultCountLimit: Int = 30,
        @Query("page") page: Int = 1,
    ): GithubSearchResponse

}