package com.example.jobsearchtw.core.domain.interactors

import com.example.jobsearchtw.core.domain.model.ErrorType
import com.example.jobsearchtw.core.domain.model.Result
import com.example.jobsearchtw.core.domain.model.Vacancy
import com.example.jobsearchtw.core.domain.repository.VacancyRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class VacancyInteractor @Inject constructor(
    private val repository: VacancyRepository
) {
    fun getVacancies(): Flow<Result<List<Vacancy>, ErrorType>> =
        repository.getVacancies()
}