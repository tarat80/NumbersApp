package com.example.numbersapp.presentation_app.searchscreen

import com.example.numbersapp.domain.model.NumberInfo

data class StateSSearch(
    val isLoading: Boolean = false,
    val searchString: String = "",
    val numbersAndMeans : List<NumberInfo> = listOf(),
    val errorMessage : String? = null
)
