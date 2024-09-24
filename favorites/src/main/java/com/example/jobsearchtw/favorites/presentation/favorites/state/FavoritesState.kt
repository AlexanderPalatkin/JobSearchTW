package com.example.jobsearchtw.favorites.presentation.favorites.state

import com.example.jobsearchtw.core.domain.model.Vacancy

sealed class FavoritesState {
    data object Start: FavoritesState()
    data object Loading : FavoritesState()
    class Data(val vacancies: List<Vacancy>) : FavoritesState()
    class Error(val message: String) : FavoritesState()
}