package com.season.winter.githubapp.feature.main

import androidx.activity.viewModels
import com.season.winter.core.common.activity.BaseActivity
import com.season.winter.core.common.domain.FragmentService
import com.season.winter.feature.github.fragment.GithubUserSearchFragment
import com.season.winter.feature.github.viewmodel.GithubViewModel
import com.season.winter.githubapp.R
import com.season.winter.githubapp.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity: BaseActivity<ActivityMainBinding>(R.layout.activity_main) {

    private val viewModel: GithubViewModel by viewModels()

    @Inject
    lateinit var fragmentManager: FragmentService

    override fun ActivityMainBinding.initView() {
        activity = this@MainActivity
        fragmentManager.startFragment(
            fragmentContainer,
            GithubUserSearchFragment()
        )
    }
}