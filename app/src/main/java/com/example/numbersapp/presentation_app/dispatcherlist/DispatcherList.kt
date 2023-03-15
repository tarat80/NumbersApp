package com.example.numbersapp.presentation_app.dispatcherlist

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import javax.inject.Inject

interface DispatcherList {
    fun main(): CoroutineDispatcher
    fun io(): CoroutineDispatcher

    class Base @Inject constructor() : DispatcherList {
        override fun main(): CoroutineDispatcher = Dispatchers.Main
        override fun io(): CoroutineDispatcher = Dispatchers.IO
    }
}

