package com.example.jobsearchtw.core.domain.repository

import com.example.jobsearchtw.core.domain.model.Offer
import kotlinx.coroutines.flow.Flow
import com.example.jobsearchtw.core.domain.model.Result
import com.example.jobsearchtw.core.domain.model.Vacancy

interface OfferVacancyRepository<E> {
    fun getOffers(): Flow<Result<List<Offer>, E>>
    fun getVacancies() : Flow<Result<List<Vacancy>, E>>
}