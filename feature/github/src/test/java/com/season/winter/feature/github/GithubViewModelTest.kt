package com.season.winter.feature.github

import com.season.winter.feature.github.viewmodel.GithubViewModel
import com.season.winter.githubapp.core.domain.GithubRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.TestDispatcher
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.Assert
import org.junit.Test
import org.mockito.kotlin.mock

@OptIn(ExperimentalCoroutinesApi::class)
class GithubViewModelTest {

    private val dispatcher: TestDispatcher = UnconfinedTestDispatcher()

    private val gitHubSearchRepository = mock<GithubRepository>()

    private val viewModel = GithubViewModel(
        repository = gitHubSearchRepository,
        isTest = true
    )

    private fun initBeforeDispatchers() {
        Dispatchers.setMain(dispatcher)
    }

    private fun initAfterDispatchers() {
        Dispatchers.resetMain()
    }

    @Test
    fun `test initData`() {
        initBeforeDispatchers()
        Assert.assertTrue(true)
        Assert.assertEquals("", viewModel.currentQuery)
        Assert.assertEquals("", viewModel.lastQuery)
        Assert.assertEquals(null, viewModel.summaryJob)
        Assert.assertEquals(null, viewModel.searchJob)
        initAfterDispatchers()
    }

    @Test
    fun `test onClickSearchButton`() {
//        initBeforeDispatchers()
//
//        val searchQuery = "winter"
//        viewModel.currentQuery = searchQuery
//
//        whenever(
//            gitHubSearchRepository.getSearchUserResultStream(searchQuery)
//        )
//        runTest(dispatcher) {
//            viewModel.onSearchResultStream.test {
//                expectNoEvents()
//                cancelAndConsumeRemainingEvents()
//            }
//        }
//        viewModel.onClickSearchButton()
//
//        initAfterDispatchers()
    }

    @Test
    fun `test onClickSearchButton - fail case`() = runTest(dispatcher) {

    }

}





