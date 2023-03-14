package com.example.numbersapp.di

import android.app.Application
import androidx.room.Room
import com.example.numbersapp.data.local.NumbersDataBase
import com.example.numbersapp.data.remote.NumbersAPI
import com.example.numbersapp.data.repository.RepositoryImpl
import com.example.numbersapp.domain.Repository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.scalars.ScalarsConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DataModule {

    @Provides
    @Singleton
    fun provideNumbersAPI(): NumbersAPI {
        return Retrofit.Builder()
            .baseUrl(NumbersAPI.BASE_URL)
            .addConverterFactory(ScalarsConverterFactory.create())
            .build()
            .create(NumbersAPI::class.java)
    }

    @Provides
    @Singleton
    fun provideRoomDataBase(app: Application): NumbersDataBase {
        return Room.databaseBuilder(
            app,
            NumbersDataBase::class.java,
            "numbers.db"
        )
            .fallbackToDestructiveMigration()
            .build()
    }
    @Provides
    @Singleton
    fun provideRepository(
        api: NumbersAPI,
        db: NumbersDataBase
    ) : Repository {
        return RepositoryImpl( api, db )
    }

}