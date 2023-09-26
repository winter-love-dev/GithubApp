package com.season.winter.feature.github.fragment

import androidx.fragment.app.activityViewModels
import androidx.lifecycle.lifecycleScope
import com.season.winter.core.common.fragment.BaseFragment
import com.season.winter.core.common.util.LayoutManagerType
import com.season.winter.feature.github.R
import com.season.winter.feature.github.databinding.FragmentGithubUserSearchBinding
import com.season.winter.feature.github.recyclerview.SearchGithubUserResultAdapter
import com.season.winter.feature.github.viewmodel.GithubViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class GithubUserSearchFragment: BaseFragment<FragmentGithubUserSearchBinding>(R.layout.fragment_github_user_search) {

    private val viewModel: GithubViewModel by activityViewModels()

    override fun FragmentGithubUserSearchBinding.initViewCreated() {

        layoutManagerType = LayoutManagerType.Linear
        adapter = SearchGithubUserResultAdapter()
//        BR.adapter = SearchGithubUserResultAdapter()

        lifecycleScope.launch {
            val result = viewModel.callTest()
            adapter?.addNewResult(result)
        }
    }
}