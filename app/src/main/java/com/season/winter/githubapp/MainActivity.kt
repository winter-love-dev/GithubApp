package com.season.winter.githubapp

import androidx.activity.viewModels
import androidx.fragment.app.viewModels
import com.season.winter.core.common.activity.BaseActivity
import com.season.winter.core.common.fragment.safeCommit
import com.season.winter.feature.github.GithubUserSearchFragment
import com.season.winter.feature.github.GithubViewModel
import com.season.winter.githubapp.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity: BaseActivity<ActivityMainBinding>(R.layout.activity_main) {

    private val viewModel: GithubViewModel by viewModels()

    override fun ActivityMainBinding.initView() {
        greeting = "hello~"

        supportFragmentManager.beginTransaction().run {
            replace(fragmentContainer.id, GithubUserSearchFragment())
            addToBackStack(GithubUserSearchFragment::class.java.simpleName)
            safeCommit()
        }
    }
}