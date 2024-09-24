package com.example.jobsearchtw.network.data.repository

import com.example.jobsearchtw.core.domain.model.ErrorType
import com.example.jobsearchtw.core.domain.model.Result
import com.example.jobsearchtw.core.domain.model.Vacancy
import com.example.jobsearchtw.core.domain.repository.FavoritesRepository
import com.example.jobsearchtw.network.data.api.ApiService
import com.example.jobsearchtw.network.data.api.NetworkParams
import com.example.jobsearchtw.network.data.api.mapToErrorType
import com.example.jobsearchtw.network.data.mappers.toVacancy
import com.example.jobsearchtw.network.data.model.dto.ApiRequestDTO
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class FavoritesRepositoryImpl @Inject constructor(
    private val service: ApiService
) : FavoritesRepository {

    override fun getFavorites() = flow<Result<List<Vacancy>, ErrorType>> {
        val response = service.getData(NetworkParams.DOWNLOAD_URL)
        when (val body = response.body()) {
            is ApiRequestDTO -> {
                emit(Result.Success(body.vacancies
                    .filter { it.isFavorite }
                    .map { it.toVacancy() }))
            }

            else -> {
                emit(Result.Error(response.code().mapToErrorType()))
            }
        }
    }.flowOn(Dispatchers.IO)
}