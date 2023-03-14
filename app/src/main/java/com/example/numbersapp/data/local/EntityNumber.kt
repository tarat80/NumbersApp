package com.example.numbersapp.data.local

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.numbersapp.domain.model.NumberInfo

@Entity
data class EntityNumber(
    @ColumnInfo(name = "number_") val number: String,
    @PrimaryKey @ColumnInfo(name = "mean_") val mean: String
){
    fun toNumberInfo() = NumberInfo(
        number=number,
        mean = mean
    )
}