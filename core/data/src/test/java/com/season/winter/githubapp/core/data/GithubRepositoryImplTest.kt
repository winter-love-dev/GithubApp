package com.season.winter.githubapp.core.data

import com.season.winter.githubapp.core.data.database.GithubLocalDatabase
import com.season.winter.githubapp.core.domain.GithubApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.TestDispatcher
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.setMain
import org.junit.Assert
import org.junit.Test
import org.mockito.kotlin.mock

@OptIn(ExperimentalCoroutinesApi::class)
class GithubRepositoryImplTest {

    private val dispatcher: TestDispatcher = UnconfinedTestDispatcher()

    private val githubApi = mock<GithubApi>()
    private val githubLocalDatabase = mock<GithubLocalDatabase>()


    // repository test 가능한 형태의 코드를 고민 해보자...
    // 지금 당장은 어떤 테스트를 해야될지 잘 모르겠다.
    private val repository = GithubRepositoryImpl(
        remoteDao = githubApi,
        database = githubLocalDatabase,
    )

    fun initBeforeDispatchers() {
        Dispatchers.setMain(dispatcher)
    }

    fun initAfterDispatchers() {
        Dispatchers.resetMain()
    }

//    @Test
//    fun `test initData`() {
//        Assert.assertTrue(true)
//        Assert.assertFalse(false)
//        Assert.assertEquals(4, 2 + 2)
//    }

}