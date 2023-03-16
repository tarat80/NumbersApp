package com.example.numbersapp.data.repository

import com.example.numbersapp.data.local.EntityNumber
import com.example.numbersapp.data.local.MapperDataToDomain
import com.example.numbersapp.data.local.NumbersDataBase
import com.example.numbersapp.data.remote.NumbersAPI
import com.example.numbersapp.domain.Repository
import com.example.numbersapp.domain.model.NumberInfo
import com.example.numbersapp.domain.model.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class RepositoryImpl @Inject constructor(
    private val numbersAPI: NumbersAPI,
    private val numbersDataBase: NumbersDataBase,
    private val mapper: MapperDataToDomain
) : Repository {

    override suspend fun getNumberInfo(number: String): Flow<Resource<List<NumberInfo>>> {
        val dao = numbersDataBase.numberDAO()

        return flow {
            emit(Resource.Loading())

            try {
                val data = if (number.isNotBlank()) {
                    numbersAPI.getNumberInfo(number)
                } else {
                    numbersAPI.getRandomNumberInfo()
                }

                dao.insertNumber(
                    EntityNumber(
                        mean = data.substringAfter(" "),
                        number = data.substringBefore(" ")
                    )
                )
                val temp = dao.getAll().map { it.map(mapper) }
                emit(Resource.Success(temp))

            } catch (e: HttpException) {
                emit(Resource.Error("Oops, something went wrong!"))

            } catch (e: IOException) {
                emit(
                    Resource.Error(
                        "Couldn't reach server, check your internet connection."
                    )
                )
            }
            emit(Resource.Loading(false))
        }
    }

  /*  override suspend fun getCashed(): Flow<Resource<List<NumberInfo>>> {
        TODO("Not yet implemented")
    }*/
}