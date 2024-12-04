package com.example.project.di

import com.example.project.data.networking.ApiServices
import com.example.project.data.repository.NabbraRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {
    @Provides
    @Singleton
    fun provideNabbraRepository(apiServices: ApiServices): NabbraRepository {
        return NabbraRepository(apiServices)
    }
}