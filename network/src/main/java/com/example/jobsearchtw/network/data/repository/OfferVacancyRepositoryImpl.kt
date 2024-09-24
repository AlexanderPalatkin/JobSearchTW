package com.example.jobsearchtw.network.data.repository

import com.example.jobsearchtw.core.domain.model.ErrorType
import com.example.jobsearchtw.core.domain.model.Offer
import com.example.jobsearchtw.core.domain.model.Result
import com.example.jobsearchtw.core.domain.model.Vacancy
import com.example.jobsearchtw.core.domain.repository.OfferVacancyRepository
import com.example.jobsearchtw.network.data.api.ApiService
import com.example.jobsearchtw.network.data.api.NetworkParams
import com.example.jobsearchtw.network.data.api.mapToErrorType
import com.example.jobsearchtw.network.data.mappers.toOffer
import com.example.jobsearchtw.network.data.mappers.toVacancy
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class OfferVacancyRepositoryImpl @Inject constructor(
    private val service: ApiService
) : OfferVacancyRepository {
    override fun getOffers() = flow<Result<List<Offer>, ErrorType>> {
        try {
            val response = service.getData(NetworkParams.DOWNLOAD_URL)
            if (response.isSuccessful) {
                val body = response.body()
                if (body != null) {
                    emit(Result.Success(body.offers.map { it.toOffer() }))
                } else {
                    emit(Result.Error(ErrorType.NOT_FOUND))
                }
            } else {
                emit(Result.Error(response.code().mapToErrorType()))
            }
        } catch (e: Exception) {
            emit(Result.Error(ErrorType.UNKNOWN_ERROR))
        }
    }.flowOn(Dispatchers.IO)

    override fun getVacancies() = flow<Result<List<Vacancy>, ErrorType>> {

            val response = service.getData(NetworkParams.DOWNLOAD_URL)
            if (response.isSuccessful) {
                val body = response.body()
                if (body != null) {
                    emit(Result.Success(body.vacancies.map { it.toVacancy() }))
                } else {
                    emit(Result.Error(ErrorType.NOT_FOUND))
                }
            } else {
                emit(Result.Error(response.code().mapToErrorType()))
            }

    }.flowOn(Dispatchers.IO)
}