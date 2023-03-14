package com.example.numbersapp.di

import com.example.numbersapp.presentation_app.dispatcherprovider.DefaultDispatcherProvider
import com.example.numbersapp.presentation_app.dispatcherprovider.DispatcherProvider
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped

@Module
@InstallIn(ViewModelComponent::class)
class AppModule {

    @Provides
    @ViewModelScoped
    fun provideDispatcher(): DispatcherProvider {
      return DefaultDispatcherProvider()
    }
}