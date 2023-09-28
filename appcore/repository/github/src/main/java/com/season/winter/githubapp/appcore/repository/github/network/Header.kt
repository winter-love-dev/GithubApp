package com.season.winter.githubapp.appcore.repository.github.network

import com.season.winter.githubapp.appcore.repository.github.BuildConfig
import okhttp3.Interceptor
import okhttp3.Response
import java.io.IOException

internal sealed class Header(
    val key: String,
    val value: String,
) {
    object Version : Header("X-GitHub-Api-Version", "2022-11-28")
    object Accept : Header("Accept", "application/vnd.github+json")
    object Authorization : Header("Authorization", BuildConfig.GITHUB_REST_API_ACCESS_KEY)
}

internal class HeaderSettingInterceptor : Interceptor {

    @Throws(IOException::class)
    override fun intercept(chain: Interceptor.Chain): Response {

        val chainRequest = chain.request()

        val request = chainRequest.newBuilder().apply {
            Header.Version.also { addHeader(it.key, it.value) }
            Header.Accept.also { addHeader(it.key, it.value) }
            Header.Authorization.also { addHeader(it.key, it.value) }
        }.build()

        return chain.proceed(request)
    }
}