package com.example.jobsearchtw.core.domain.interactors

import com.example.jobsearchtw.core.domain.model.Result
import com.example.jobsearchtw.core.domain.model.Vacancy
import com.example.jobsearchtw.core.domain.repository.VacancyRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class VacancyInteractor<E> @Inject constructor(
    private val repository: VacancyRepository<E>
) {
    fun getVacancies(): Flow<Result<List<Vacancy>, E>> =
        repository.getVacancies()
}