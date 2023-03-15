package com.example.numbersapp.data.local

import androidx.room.*

@Dao
interface NumberDAO {

    @Query("SELECT * FROM entitynumber")
    suspend fun getAll(): List<EntityNumber>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertNumber(entityNumber: EntityNumber)

}