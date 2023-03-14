package com.example.numbersapp.data.local

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(
    entities = [EntityNumber::class],
    version = 3
)
abstract class NumbersDataBase : RoomDatabase(){

    abstract fun numberDAO() : NumberDAO
}