package com.season.winter.feature.github.fragment

import androidx.fragment.app.activityViewModels
import androidx.paging.PagingData
import com.season.winter.core.common.extension.coroutine.repeatOnLifecycleJob
import com.season.winter.core.common.fragment.BaseFragment
import com.season.winter.feature.github.BR
import com.season.winter.feature.github.R
import com.season.winter.feature.github.databinding.FragmentGithubUserSearchBinding
import com.season.winter.feature.github.recyclerview.SearchGithubUserResultAdapter
import com.season.winter.feature.github.viewmodel.GithubViewModel
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class GithubUserSearchFragment: BaseFragment<FragmentGithubUserSearchBinding>(R.layout.fragment_github_user_search) {

    @Inject
    lateinit var githubViewModelFactory: GithubViewModel.GithubViewModelAssistedFactory

    val viewModel by activityViewModels<GithubViewModel> {
        GithubViewModel.provideFactory(githubViewModelFactory, false)
    }

    override fun FragmentGithubUserSearchBinding.initViewCreated() {
        binding.setVariable(BR.fragment, this@GithubUserSearchFragment)
        binding.setVariable(BR.github_view_model, viewModel)

        adapter = SearchGithubUserResultAdapter(viewModel)

        repeatOnLifecycleJob(viewModel.onClearedCache) {
            if (it) {
                adapter?.submitData(PagingData.empty())
                count = ""
            }
        }
        repeatOnLifecycleJob(viewModel.onSearchResultStream) {
            adapter?.submitData(it)
        }
        repeatOnLifecycleJob(viewModel.onSearchResultSummaryStream) {
            count = if (it == null) "" else "total: ${it.totalCount}"
        }
    }

    override fun onDestroyView() {
        viewModel.clearSearchUserCache()
        super.onDestroyView()
    }

    companion object {

        private const val TAG = "GithubUserSearchFragment"
    }

}