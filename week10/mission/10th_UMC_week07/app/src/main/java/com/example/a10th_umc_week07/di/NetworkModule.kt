package com.example.a10th_umc_week07.di

import com.example.a10th_umc_week07.data.remote.ApiService
import com.example.a10th_umc_week07.domain.repository.AuthRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Provides
    @Singleton
    fun provideAuthRepository(apiService: ApiService): AuthRepository {
        return AuthRepository(apiService)
    }
}