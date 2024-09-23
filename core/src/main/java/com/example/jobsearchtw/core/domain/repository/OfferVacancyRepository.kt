package com.example.jobsearchtw.core.domain.repository

import com.example.jobsearchtw.core.domain.model.ErrorType
import com.example.jobsearchtw.core.domain.model.Offer
import kotlinx.coroutines.flow.Flow
import com.example.jobsearchtw.core.domain.model.Result
import com.example.jobsearchtw.core.domain.model.Vacancy

interface OfferVacancyRepository {
    fun getOffers(): Flow<Result<List<Offer>, ErrorType>>
    fun getVacancies() : Flow<Result<List<Vacancy>, ErrorType>>
}