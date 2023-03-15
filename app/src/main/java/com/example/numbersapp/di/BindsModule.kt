package com.example.numbersapp.di

import com.example.numbersapp.data.repository.RepositoryImpl
import com.example.numbersapp.domain.Repository
import com.example.numbersapp.presentation_app.dispatcherlist.DispatcherList
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
interface BindsModule {
    @Binds
    fun bindRepository(
        repositoryImpl:
        RepositoryImpl
    ): Repository

    @Binds
    fun bindDispatchers(
        base: DispatcherList.Base
    ): DispatcherList
}