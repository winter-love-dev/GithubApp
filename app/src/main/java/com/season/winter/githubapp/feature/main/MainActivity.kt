package com.season.winter.githubapp.feature.main

import androidx.activity.viewModels
import com.season.winter.core.common.activity.BaseActivity
import com.season.winter.feature.github.fragment.GithubUserSearchFragment
import com.season.winter.feature.github.viewmodel.GithubViewModel
import com.season.winter.githubapp.R
import com.season.winter.githubapp.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity: BaseActivity<ActivityMainBinding>(R.layout.activity_main) {

    @Inject
    lateinit var githubViewModelFactory: GithubViewModel.GithubViewModelAssistedFactory

    // activity의 뷰모델 인스턴스를 fragment로 사용하기 위한 초기화
    // and initial for testing
    private val viewModel by viewModels<GithubViewModel> {
        GithubViewModel.provideFactory(githubViewModelFactory, false)
    }

    override fun ActivityMainBinding.initView() {
        supportFragmentManager.beginTransaction().let {
            it.replace(fragmentContainer.id, GithubUserSearchFragment())
            it.commit()
        }
    }
}