package com.example.jobsearchtw.favorites.presentation.favorites.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.jobsearchtw.core.domain.model.ErrorType
import com.example.jobsearchtw.core.domain.model.Result
import com.example.jobsearchtw.core.domain.model.Vacancy
import com.example.jobsearchtw.core.domain.interactors.FavoritesInteractor
import com.example.jobsearchtw.favorites.presentation.favorites.state.FavoritesState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FavoritesViewModel @Inject constructor(
    private val interactor: FavoritesInteractor
) : ViewModel() {
    private val _state = MutableStateFlow<FavoritesState>(FavoritesState.Start)
    val state = _state.asStateFlow()

    init {
        getData()
    }

    private fun getData() {
        _state.update { FavoritesState.Loading }

        viewModelScope.launch {
            interactor.getFavorites().collect { result ->
                processLoadFavorites(result)
            }
        }
    }

    private fun processLoadFavorites(result: Result<List<Vacancy>, ErrorType>) {
        when (result) {
            is Result.Success -> {
                _state.update { FavoritesState.Data(result.data) }
            }

            is Result.Error -> {
                _state.update { FavoritesState.Error(result.error.message) }
            }
        }
    }
}