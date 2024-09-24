package com.example.jobsearchtw.search.presentation.search_all_vacancies.state

import com.example.jobsearchtw.core.domain.model.Vacancy

sealed class AllVacanciesState {
    data object Start: AllVacanciesState()
    data object Loading : AllVacanciesState()
    class Data(val vacancies: List<Vacancy>) : AllVacanciesState()
    class Error(val message: String) : AllVacanciesState()
}