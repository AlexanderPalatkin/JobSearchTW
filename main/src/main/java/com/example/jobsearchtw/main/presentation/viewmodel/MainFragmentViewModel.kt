package com.example.jobsearchtw.main.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.jobsearchtw.core.domain.interactors.FavoritesInteractor
import com.example.jobsearchtw.core.domain.model.ErrorType
import com.example.jobsearchtw.core.domain.model.Result
import com.example.jobsearchtw.core.domain.model.Vacancy
import com.example.jobsearchtw.main.presentation.state.MainFavoritesState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainFragmentViewModel @Inject constructor(
    private val interactor: FavoritesInteractor
) : ViewModel() {
    private val _state = MutableStateFlow<MainFavoritesState>(MainFavoritesState.Start)
    val state = _state.asStateFlow()

    init {
        getData()
    }

    private fun getData() {
        _state.update { MainFavoritesState.Loading }

        viewModelScope.launch {
            interactor.getFavorites().collect { result ->
                processLoadCountFavorites(result)
            }
        }
    }

    private fun processLoadCountFavorites(result: Result<List<Vacancy>, ErrorType>) {
        when (result) {
            is Result.Success -> {
                _state.update { MainFavoritesState.Data(result.data.size) }
            }

            is Result.Error -> {
                _state.update { MainFavoritesState.Error(result.error.message) }
            }
        }
    }
}