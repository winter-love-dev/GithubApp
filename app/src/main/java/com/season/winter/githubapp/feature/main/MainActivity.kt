package com.season.winter.githubapp.feature.main

import androidx.activity.viewModels
import com.season.winter.core.common.activity.BaseActivity
import com.season.winter.feature.github.fragment.GithubUserSearchFragment
import com.season.winter.feature.github.viewmodel.GithubViewModel
import com.season.winter.githubapp.R
import com.season.winter.githubapp.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity: BaseActivity<ActivityMainBinding>(R.layout.activity_main) {

    // activity의 뷰모델 인스턴스를 fragment로 사용하기 위한 초기화
    private val viewModel: GithubViewModel by viewModels()

    override fun ActivityMainBinding.initView() {
        supportFragmentManager.beginTransaction().let {
            it.replace(fragmentContainer.id, GithubUserSearchFragment())
            it.commit()
        }
    }
}