package com.example.numbersapp.presentation_app.searchscreen

import app.cash.turbine.test
import com.example.numbersapp.domain.Repository
import com.example.numbersapp.domain.model.NumberInfo
import com.example.numbersapp.domain.model.Resource
import com.example.numbersapp.presentation_app.dispatcherlist.DispatcherList
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.runTest
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.doReturn
import org.mockito.Mockito.verify
import org.mockito.junit.MockitoJUnitRunner

@ExperimentalCoroutinesApi
@RunWith(MockitoJUnitRunner::class)
class ScreenSearchVMTest {

    private lateinit var dispatcherProvider: DispatcherList

    @Mock
    private lateinit var repository: Repository

    @Before
    fun setup() {
        dispatcherProvider = TestDispatcherProvider()
    }
    @Test
    fun `ask repository and loading started`() {
        runTest {
            doReturn(flowOf(Resource.Loading<List<NumberInfo>>(true)))
                .`when`(repository).getNumberInfo(number = "")

            val viewModel = ScreenSearchVM(repository, dispatcherProvider)
            viewModel.getByNumber()
            viewModel.state.test {
                assertEquals(StateSSearch(isLoading = true), awaitItem())
                cancelAndIgnoreRemainingEvents()
            }
            verify(repository).getNumberInfo("")
        }
    }
    @Test
    fun `ask repository and got error`() {
        runTest {
            doReturn(flowOf(Resource.Error<List<NumberInfo>>("something")))
                .`when`(repository).getNumberInfo(number = "")

            val viewModel = ScreenSearchVM(repository, dispatcherProvider)
            viewModel.getByNumber()
            viewModel.state.test {
                assertEquals(StateSSearch(errorMessage = "something"), awaitItem())
                cancelAndIgnoreRemainingEvents()
            }
            verify(repository).getNumberInfo("")
        }
    }
    @Test
    fun `ask repository and got data`() {
        runTest {

            val temp = listOf(NumberInfo(number = "666", mean ="devil number" ))
            doReturn(flowOf(Resource.Success(temp)))
                .`when`(repository).getNumberInfo("666")

            val viewModel = ScreenSearchVM(repository, dispatcherProvider)
            viewModel.searchChanged("666")
            viewModel.getByNumber()
            viewModel.state.test {
                assertEquals(StateSSearch(numbersAndMeans = temp, searchString = "666"), awaitItem())
                cancelAndIgnoreRemainingEvents()
            }
            verify(repository).getNumberInfo("666")
        }
    }
    @Test
    fun `error appear and user dismissed it`(){
        runTest {
            doReturn(flowOf(Resource.Error<List<NumberInfo>>("something")))
                .`when`(repository).getNumberInfo(number = "")

            val viewModel = ScreenSearchVM(repository, dispatcherProvider)
            viewModel.getByNumber()
            viewModel.errorHaveRead()
            viewModel.state.test {
                assertEquals(StateSSearch(errorMessage = null), awaitItem())
                cancelAndIgnoreRemainingEvents()
            }
            verify(repository).getNumberInfo("")
        }
    }
    @Test
    fun `user added search string`(){
        runTest {
            val viewModel = ScreenSearchVM(repository, dispatcherProvider)
            viewModel.searchChanged("666")
            viewModel.state.test {
                assertEquals(StateSSearch(searchString = "666"), awaitItem())
                cancelAndIgnoreRemainingEvents()
            }
        }
    }
}
