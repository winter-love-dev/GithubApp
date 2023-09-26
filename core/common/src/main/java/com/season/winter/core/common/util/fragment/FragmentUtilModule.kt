package com.season.winter.core.common.util.fragment

import android.content.Context
import androidx.fragment.app.FragmentActivity
import com.season.winter.core.common.domain.FragmentService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent
import dagger.hilt.android.qualifiers.ActivityContext

@Module
@InstallIn(ActivityComponent::class)
object FragmentUtilModule {

    @Provides
    fun provideFragmentService(
        @ActivityContext context: Context,
    ): FragmentService {

        return FragmentServiceImpl(context as FragmentActivity)
    }
}