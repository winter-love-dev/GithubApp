package com.season.winter.core.common.fragment.util

import android.content.Context
import androidx.fragment.app.FragmentActivity
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ActivityContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object FragmentUtilModule {

    @Provides
    @Singleton
    fun provideFragmentService(
        @ActivityContext context: Context,
    ): FragmentService {

        return FragmentServiceImpl(context as FragmentActivity)
    }
}