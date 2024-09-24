package com.example.jobsearchtw.favorites.presentation.favorites

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.example.jobsearchtw.base.presentation.BaseFragment
import com.example.jobsearchtw.common.presentation.utils.VacanciesAdapter
import com.example.jobsearchtw.core.domain.model.Vacancy
import com.example.jobsearchtw.favorites.R
import com.example.jobsearchtw.favorites.databinding.FragmentFavoritesBinding
import com.example.jobsearchtw.favorites.presentation.favorites.state.FavoritesState
import com.example.jobsearchtw.favorites.presentation.favorites.viewmodel.FavoritesViewModel
import com.example.jobsearchtw.uikit.ItemOffsetDecoration
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class FavoritesFragment : BaseFragment<FragmentFavoritesBinding>(FragmentFavoritesBinding::inflate) {
    private val viewModel by viewModels<FavoritesViewModel>()
    private val favoritesAdapter by lazy { VacanciesAdapter() }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        lifecycleScope.launch {
            viewModel.state.collect { state ->
                renderState(state)
            }
        }
    }

    private fun renderState(state: FavoritesState) {
        when (state) {
            is FavoritesState.Data -> {
                binding.tvVacancyFavorites.text = binding.root.context.resources.getQuantityString(
                    R.plurals.vacancy_count_favorites, state.vacancies.size, state.vacancies.size
                )
                initRecyclerFavorites(state.vacancies)
            }

            is FavoritesState.Error -> {
                Log.d("Favorites_FRAGMENT", state.message)
            }

            is FavoritesState.Loading, FavoritesState.Start -> {
                // Здесь можно показать индикатор загрузки, если нужно
            }
        }
    }

    private fun initRecyclerFavorites(favoritesList: List<Vacancy>) {
        val itemDecoration = ItemOffsetDecoration(0, 0, 0, 8)
        binding.rvFavorites.addItemDecoration(itemDecoration)
        binding.rvFavorites.adapter = createFavoritesAdapter(favoritesList)
    }

    private fun createFavoritesAdapter(favoritesList: List<Vacancy>) =
        favoritesAdapter.apply {
            submitList(favoritesList)

            onCardClickListener = {
                findNavController().navigate(R.id.action_favoritesFragment_to_favoriteVacancyFragment)
            }
            onRespondClickListener = {
                Toast.makeText(requireContext(), "Откликнуться", Toast.LENGTH_SHORT).show()
            }
        }
}