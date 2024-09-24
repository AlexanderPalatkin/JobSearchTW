package com.example.jobsearchtw.search.presentation.search_all_vacancies

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
import com.example.jobsearchtw.search.R
import com.example.jobsearchtw.search.databinding.FragmentSearchAllVacanciesBinding
import com.example.jobsearchtw.search.presentation.search_all_vacancies.state.AllVacanciesState
import com.example.jobsearchtw.search.presentation.search_all_vacancies.viewmodel.SearchAllVacanciesViewModel
import com.example.jobsearchtw.uikit.ItemOffsetDecoration
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class SearchAllVacanciesFragment :
    BaseFragment<FragmentSearchAllVacanciesBinding>(FragmentSearchAllVacanciesBinding::inflate) {

    private val viewModel by viewModels<SearchAllVacanciesViewModel>()
    private val allVacanciesAdapter by lazy { VacanciesAdapter() }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        lifecycleScope.launch {
            viewModel.state.collect { state ->
                renderState(state)
            }
        }
    }

    private fun renderState(state: AllVacanciesState) {
        when (state) {
            is AllVacanciesState.Data -> {
                binding.tvCountVacancy.text = binding.root.context.resources.getQuantityString(
                    R.plurals.all_vacancy_count, state.vacancies.size, state.vacancies.size
                )
                initRecyclerVacancies(state.vacancies)
            }

            is AllVacanciesState.Error -> {
                Log.d("SEARCH_ALL_VACANCIES_FRAGMENT", state.message)
            }

            is AllVacanciesState.Loading, AllVacanciesState.Start -> {
                // Здесь можно показать индикатор загрузки, если нужно
            }
        }
    }

    private fun initRecyclerVacancies(vacanciesList: List<Vacancy>) {
        val itemDecoration = ItemOffsetDecoration(0, 8, 0, 4)
        binding.rvAllVacancies.addItemDecoration(itemDecoration)
        binding.rvAllVacancies.adapter = createVacanciesAdapter(vacanciesList)
    }

    private fun createVacanciesAdapter(vacanciesList: List<Vacancy>) =
        allVacanciesAdapter.apply {
            submitList(vacanciesList)

            onCardClickListener = {
                findNavController().navigate(R.id.action_searchAllVacanciesFragment_to_searchVacancyFragment)
            }
            onRespondClickListener = {
                Toast.makeText(requireContext(), "Откликнуться", Toast.LENGTH_SHORT).show()
            }
        }
}