package com.season.winter.feature.github.fragment

import android.util.Log
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.lifecycleScope
import androidx.paging.PagingData
import androidx.paging.map
import com.season.winter.core.common.extension.coroutine.repeatOnLifecycleJob
import com.season.winter.core.common.fragment.BaseFragment
import com.season.winter.core.common.util.LayoutManagerType
import com.season.winter.feature.github.BR
import com.season.winter.feature.github.R
import com.season.winter.feature.github.databinding.FragmentGithubUserSearchBinding
import com.season.winter.feature.github.recyclerview.SearchGithubUserResultAdapter
import com.season.winter.feature.github.viewmodel.GithubViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch

@AndroidEntryPoint
class GithubUserSearchFragment: BaseFragment<FragmentGithubUserSearchBinding>(R.layout.fragment_github_user_search) {

    val viewModel: GithubViewModel by activityViewModels()

    private var summaryJob: Job? = null
    private var searchJob: Job? = null

    override fun FragmentGithubUserSearchBinding.initViewCreated() {
        binding.setVariable(BR.fragment, this@GithubUserSearchFragment)
        binding.setVariable(BR.github_view_model, viewModel)

        layoutManagerType = LayoutManagerType.Linear
        adapter = SearchGithubUserResultAdapter(viewModel)

        repeatOnLifecycleJob(viewModel.onClearedCache) {
            if (it) onClickClearResult()
        }
    }

    fun onClickSearchButton() {
        searchJob?.cancel()
        searchJob = lifecycleScope.launch {
            viewModel.getSearchUserStream()
                .map { pagingData ->
                    Log.e(TAG, "onClickSearchButton: pagingData: $pagingData")
                    pagingData.map { user ->
                        Log.e(TAG, "onClickSearchButton: user: $user")
                        user
                    }
                    pagingData
                }
                .collectLatest {
                    binding.adapter?.submitData(it)
                }
        }
        summaryJob?.cancel()
        summaryJob = lifecycleScope.launch {
            viewModel.getTotalCountStream().collectLatest {
                Log.e(TAG, "onClickSearchButton: summary: $it")
                binding.count = if (it == null) "" else "total: ${it.totalCount}"
            }
        }
    }

    fun onClickClearResult() {
        lifecycleScope.launch {
            binding.adapter?.submitData(PagingData.empty())
            binding.count = ""
        }
    }

    override fun onDestroyView() {
        // destroy 되기전에 캐시 날리기. 캐시 날리는 것보다 destroy 속도가 빠를 수 있을까?
        viewModel.clearSearchUserCache()
        super.onDestroyView()
    }

    companion object {

        private const val TAG = "GithubUserSearchFragment"
    }

}