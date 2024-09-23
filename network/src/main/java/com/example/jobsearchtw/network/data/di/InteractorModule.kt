package com.example.jobsearchtw.network.data.di

import com.example.jobsearchtw.core.domain.interactors.OfferVacancyInteractor
import com.example.jobsearchtw.core.domain.interactors.VacancyInteractor
import com.example.jobsearchtw.core.domain.repository.OfferVacancyRepository
import com.example.jobsearchtw.core.domain.repository.VacancyRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class InteractorModule {

    @Provides
    @Singleton
    fun provideOfferVacancyInteractor(
        repository: OfferVacancyRepository
    ): OfferVacancyInteractor = OfferVacancyInteractor(repository)

    @Provides
    @Singleton
    fun provideVacancyInteractor(
        repository: VacancyRepository
    ): VacancyInteractor = VacancyInteractor(repository)
}