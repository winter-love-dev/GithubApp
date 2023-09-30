package com.season.winter.githubapp.appcore.repository.github.network

import com.season.winter.githubapp.appcore.repository.github.BuildConfig
import okhttp3.Interceptor
import okhttp3.Response

internal class HeaderSettingInterceptor : Interceptor {

    private val headerPair: List<Pair<String, String>> = listOf(
        "X-GitHub-Api-Version" to "2022-11-28",
        "Accept" to "application/vnd.github+json",
        "Authorization" to BuildConfig.GITHUB_REST_API_ACCESS_KEY,
    )

    override fun intercept(chain: Interceptor.Chain): Response {

        val chainRequest = chain.request()

        val request = chainRequest.newBuilder().apply {
            headerPair.forEach {
                addHeader(it.first, it.second)
            }
        }.build()

        return chain.proceed(request)
    }
}