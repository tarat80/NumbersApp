package com.example.numbersapp.data.local

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.numbersapp.domain.model.NumberInfo
import javax.inject.Inject

@Entity
data class EntityNumber(
    @ColumnInfo(name = "number_") val number: String,
    @PrimaryKey @ColumnInfo(name = "mean_") val mean: String
) {
    interface Mapper<T> { fun map(number: String, mean: String): T  }
    fun <T> map(mapper: Mapper<T>): T = mapper.map(number, mean)
}
class MapperDataToDomain @Inject constructor() :
    EntityNumber.Mapper<NumberInfo> {
    override fun map(
        number: String, mean: String
    ): NumberInfo = NumberInfo(number, mean)
}