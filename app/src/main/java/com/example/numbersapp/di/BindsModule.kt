package com.example.numbersapp.di

import com.example.numbersapp.data.repository.RepositoryImpl
import com.example.numbersapp.domain.Repository
import com.example.numbersapp.presentation_app.dispatcherprovider.DefaultDispatcherProvider
import com.example.numbersapp.presentation_app.dispatcherprovider.DispatcherProvider
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
abstract class BindsModule {
    @Binds
    abstract fun bindRepository(
        repositoryImpl:
        RepositoryImpl
    ): Repository

    @Binds
    abstract fun bindDispatcher(
        defaultDispatcherProvider:
        DefaultDispatcherProvider
    ): DispatcherProvider
}