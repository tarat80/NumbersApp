package com.example.numbersapp.presentation_app.searchscreen

import com.example.numbersapp.presentation_app.dispatcherlist.DispatcherList
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.UnconfinedTestDispatcher
@ExperimentalCoroutinesApi
class TestDispatcherProvider  : DispatcherList {
    override fun main(): CoroutineDispatcher = UnconfinedTestDispatcher()
    override fun io(): CoroutineDispatcher = UnconfinedTestDispatcher()
}
