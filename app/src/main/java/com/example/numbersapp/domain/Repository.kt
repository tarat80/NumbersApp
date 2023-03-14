package com.example.numbersapp.domain

import com.example.numbersapp.domain.model.NumberInfo
import com.example.numbersapp.domain.model.Resource
import kotlinx.coroutines.flow.Flow

interface Repository {
    suspend fun getNumberInfo(number: String): Flow<Resource<List<NumberInfo>>>

  //  suspend fun getCashed(): Flow<Resource<List<NumberInfo>>>
}