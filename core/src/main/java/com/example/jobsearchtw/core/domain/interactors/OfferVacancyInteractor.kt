package com.example.jobsearchtw.core.domain.interactors

import com.example.jobsearchtw.core.domain.model.ErrorType
import com.example.jobsearchtw.core.domain.model.Offer
import com.example.jobsearchtw.core.domain.model.Result
import com.example.jobsearchtw.core.domain.model.Vacancy
import com.example.jobsearchtw.core.domain.repository.OfferVacancyRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class OfferVacancyInteractor @Inject constructor(
    private val repository: OfferVacancyRepository
) {
    fun getOffers(): Flow<Result<List<Offer>, ErrorType>> = repository.getOffers()

    fun getVacancies(): Flow<Result<List<Vacancy>, ErrorType>> = repository.getVacancies()
}