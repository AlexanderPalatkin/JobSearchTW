package com.example.jobsearchtw.network.data.repository

import com.example.jobsearchtw.core.domain.model.Offer
import com.example.jobsearchtw.core.domain.model.Result
import com.example.jobsearchtw.core.domain.model.Vacancy
import com.example.jobsearchtw.core.domain.repository.OfferVacancyRepository
import com.example.jobsearchtw.network.data.api.ApiService
import com.example.jobsearchtw.network.data.api.ErrorType
import com.example.jobsearchtw.network.data.api.mapToErrorType
import com.example.jobsearchtw.network.data.mappers.toOffer
import com.example.jobsearchtw.network.data.mappers.toVacancy
import com.example.jobsearchtw.network.data.model.dto.ApiRequestDTO
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class OfferVacancyRepositoryImpl @Inject constructor(
    private val service: ApiService
) : OfferVacancyRepository<ErrorType> {
    private val response = service.getData()

    override fun getOffers() = flow<Result<List<Offer>, ErrorType>> {
        when (val body = response.body()) {
            is ApiRequestDTO -> {
                emit(Result.Success(body.offers.map {
                    it.toOffer()
                }))
            }

            else -> {
                emit(Result.Error(response.code().mapToErrorType()))
            }
        }
    }.flowOn(Dispatchers.IO)

    override fun getVacancies() = flow<Result<List<Vacancy>, ErrorType>> {
        when (val body = response.body()) {
            is ApiRequestDTO -> {
                emit(Result.Success(body.vacancies.map {
                    it.toVacancy()
                }))
            }

            else -> {
                emit(Result.Error(response.code().mapToErrorType()))
            }
        }
    }.flowOn(Dispatchers.IO)
}