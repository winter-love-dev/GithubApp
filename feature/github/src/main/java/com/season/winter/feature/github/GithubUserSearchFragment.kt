package com.season.winter.feature.github

import androidx.fragment.app.activityViewModels
import com.season.winter.core.common.fragment.BaseFragment
import com.season.winter.feature.github.databinding.FragmentGithubUserSearchBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class GithubUserSearchFragment: BaseFragment<FragmentGithubUserSearchBinding>(R.layout.fragment_github_user_search) {

    private val viewModel: GithubViewModel by activityViewModels()

    override fun FragmentGithubUserSearchBinding.initViewCreated() {
        greeting = "hello~~fragment"

        viewModel.callTest()
    }
}