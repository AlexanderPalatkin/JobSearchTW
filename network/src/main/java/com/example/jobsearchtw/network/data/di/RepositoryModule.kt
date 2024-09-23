package com.example.jobsearchtw.network.data.di

import com.example.jobsearchtw.core.domain.repository.OfferVacancyRepository
import com.example.jobsearchtw.core.domain.repository.VacancyRepository
import com.example.jobsearchtw.network.data.api.ApiService
import com.example.jobsearchtw.network.data.repository.OfferVacancyRepositoryImpl
import com.example.jobsearchtw.network.data.repository.VacancyRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class RepositoryModule {

    @Provides
    @Singleton
    fun provideOfferVacancyRepository(apiService: ApiService): OfferVacancyRepository =
        OfferVacancyRepositoryImpl(apiService)

    @Provides
    @Singleton
    fun provideVacancyRepository(apiService: ApiService): VacancyRepository =
        VacancyRepositoryImpl(apiService)
}