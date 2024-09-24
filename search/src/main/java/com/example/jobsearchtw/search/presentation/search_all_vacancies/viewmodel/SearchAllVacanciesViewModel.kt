package com.example.jobsearchtw.search.presentation.search_all_vacancies.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.jobsearchtw.core.domain.interactors.VacancyInteractor
import com.example.jobsearchtw.core.domain.model.ErrorType
import com.example.jobsearchtw.core.domain.model.Result
import com.example.jobsearchtw.core.domain.model.Vacancy
import com.example.jobsearchtw.search.presentation.search_all_vacancies.state.AllVacanciesState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SearchAllVacanciesViewModel @Inject constructor(
    private val interactor: VacancyInteractor
) : ViewModel() {
    private val _state = MutableStateFlow<AllVacanciesState>(AllVacanciesState.Start)
    val state = _state.asStateFlow()

    init {
        getData()
    }

    private fun getData() {
        _state.update { AllVacanciesState.Loading }

        viewModelScope.launch {
            interactor.getVacancies().collect { result ->
                processLoadVacancies(result)
            }
        }
    }

    private fun processLoadVacancies(result: Result<List<Vacancy>, ErrorType>) {
        when (result) {
            is Result.Success -> {
                _state.update { AllVacanciesState.Data(result.data) }
            }

            is Result.Error -> {
                _state.update { AllVacanciesState.Error(result.error.message) }
            }
        }
    }
}