package com.example.numbersapp.data.remote

import retrofit2.http.GET
import retrofit2.http.Path

interface NumbersAPI {

    @GET(value = "{number}")
    suspend fun getNumberInfo(
        @Path(value = "number")number: String
    ) : String

    @GET(value = "random/")
    suspend fun getRandomNumberInfo(
    ) : String

    companion object {
        const val BASE_URL = "http://numbersapi.com/"
    }
}