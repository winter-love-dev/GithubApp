package com.season.winter.feature.github.fragment

import android.util.Log
import androidx.fragment.app.activityViewModels
import com.season.winter.core.common.extension.coroutine.repeatOnLifecycle
import com.season.winter.core.common.fragment.BaseFragment
import com.season.winter.core.common.util.LayoutManagerType
import com.season.winter.feature.github.BR
import com.season.winter.feature.github.R
import com.season.winter.feature.github.databinding.FragmentGithubUserSearchBinding
import com.season.winter.feature.github.recyclerview.SearchGithubUserResultAdapter
import com.season.winter.feature.github.viewmodel.GithubViewModel
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class GithubUserSearchFragment: BaseFragment<FragmentGithubUserSearchBinding>(R.layout.fragment_github_user_search) {

    private val viewModel: GithubViewModel by activityViewModels()
    private var query = ""
    val onQueryChangeTextListener = fun(newQuery: String) {
        query = newQuery
        Log.e("TAG", "newQuery: $newQuery" )
    }

    override fun FragmentGithubUserSearchBinding.initViewCreated() {
        binding.setVariable(BR.fragment, this@GithubUserSearchFragment)
        binding.setVariable(BR.github_view_model, viewModel)

        layoutManagerType = LayoutManagerType.Linear
        adapter = SearchGithubUserResultAdapter()

        repeatOnLifecycle(viewModel.onSearchUserResult) {
            binding.count = "total: ${it.totalCount}"
            adapter?.refresh(it)
        }
    }

    fun onClickSearchButton() {
        if (query.isNotEmpty())
            viewModel.searchUser(query)
    }

    fun onClickClearResult() {
        binding.count = ""
        binding.adapter?.clear()
    }

}