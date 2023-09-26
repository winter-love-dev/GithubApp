package com.season.winter.githubapp.appcore.repository.github.network

import com.season.winter.githubapp.appcore.domain.github.domain.GithubRestApiService
import com.season.winter.githubapp.appcore.repository.github.BuildConfig
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

object GithubRestApiManager {

    private const val TimeoutSecond = 10L // default okhttp.connectTimeout = 10L

    fun create(): GithubRestApiService {
        val logger = HttpLoggingInterceptor().apply { level = HttpLoggingInterceptor.Level.BASIC }

        val client = OkHttpClient.Builder()
            .addInterceptor(HeaderSettingInterceptor())
            .addInterceptor(logger)
            .connectTimeout(TimeoutSecond, TimeUnit.SECONDS)
            .writeTimeout(TimeoutSecond, TimeUnit.SECONDS)
            .readTimeout(TimeoutSecond, TimeUnit.SECONDS)
            .build()

        return Retrofit.Builder()
            .baseUrl(BuildConfig.BASE_URL)
            .client(client)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(GithubRestApiService::class.java)
    }
}