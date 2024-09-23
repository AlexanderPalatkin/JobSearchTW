package com.example.jobsearchtw.search.presentation.search

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.example.jobsearchtw.base.presentation.BaseFragment
import com.example.jobsearchtw.search.R
import com.example.jobsearchtw.search.databinding.FragmentSearchBinding
import com.example.jobsearchtw.search.presentation.search.state.SearchVacanciesState
import com.example.jobsearchtw.search.presentation.search.viewmodel.SearchViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class SearchFragment : BaseFragment<FragmentSearchBinding>(FragmentSearchBinding::inflate) {

    private val viewModel by viewModels<SearchViewModel>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.moreVacancy.setOnClickListener {
            findNavController().navigate(R.id.action_searchFragment_to_searchVacancyFragment)
        }
        lifecycleScope.launch {
            viewModel.vacanciesState.collect { state ->
                renderVacanciesState(state)
            }
        }
    }

    private fun renderVacanciesState(state: SearchVacanciesState) {
        when(state) {
            is SearchVacanciesState.Data -> {
                with(state.vacancies[0]) {
                    binding.company.text = company
                    binding.country.text = town
                }
            }
            is SearchVacanciesState.Error -> {
                Log.d("PROFILE_FRAGMENT", state.message)
            }
            is SearchVacanciesState.Loading, SearchVacanciesState.Start -> Unit
        }
    }

}