package com.example.jobsearchtw.search.presentation.search

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.example.jobsearchtw.base.presentation.BaseFragment
import com.example.jobsearchtw.core.domain.model.Offer
import com.example.jobsearchtw.core.domain.model.Vacancy
import com.example.jobsearchtw.search.R
import com.example.jobsearchtw.search.databinding.FragmentSearchBinding
import com.example.jobsearchtw.search.presentation.search.adapters.offers.SearchOffersAdapter
import com.example.jobsearchtw.search.presentation.search.adapters.vacancies.SearchVacanciesAdapter
import com.example.jobsearchtw.search.presentation.search.state.SearchState
import com.example.jobsearchtw.search.presentation.search.viewmodel.SearchViewModel
import com.example.jobsearchtw.uikit.ItemOffsetDecoration
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class SearchFragment : BaseFragment<FragmentSearchBinding>(FragmentSearchBinding::inflate) {

    private val viewModel by viewModels<SearchViewModel>()

    private val searchOffersAdapter by lazy { SearchOffersAdapter() }
    private val searchVacanciesAdapter by lazy { SearchVacanciesAdapter() }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        lifecycleScope.launch {
            viewModel.state.collect { state ->
                renderState(state)
            }
        }
    }

    private fun renderState(state: SearchState) {
        when (state) {
            is SearchState.Data -> {
                initRecyclerOffers(state.offers)
                binding.tvVacanciesHeader.isVisible = state.vacancies.isNotEmpty()
                initRecyclerVacancies(state.vacancies, state.vacancies.size)
            }

            is SearchState.Error -> {
                Log.d("SEARCH_FRAGMENT", state.message)
            }

            is SearchState.Loading, SearchState.Start -> {
                // Здесь можно показать индикатор загрузки, если нужно
            }
        }
    }

    private fun initRecyclerOffers(offerList: List<Offer>) {
        binding.rvOffers.adapter = createOffersAdapter(offerList)
    }

    private fun initRecyclerVacancies(vacanciesList: List<Vacancy>, count: Int) {
        val itemDecoration = ItemOffsetDecoration(0, 0, 0, 16)
        binding.rvVacancies.addItemDecoration(itemDecoration)
        binding.rvVacancies.adapter = createVacanciesAdapter(vacanciesList, count)
    }

    private fun createOffersAdapter(offerList: List<Offer>) = searchOffersAdapter.apply {
        submitList(offerList)
        onOfferClickListener = { link ->
            val intent = Intent(Intent.ACTION_VIEW).apply {
                data = Uri.parse(link)
            }
            requireContext().startActivity(intent)
        }
    }

    private fun createVacanciesAdapter(vacanciesList: List<Vacancy>, count: Int) =
        searchVacanciesAdapter.apply {
            submitList(vacanciesList)

            onCardClickListener = {
                findNavController().navigate(R.id.action_searchFragment_to_searchVacancyFragment)
            }
            onRespondClickListener = {
                Toast.makeText(requireContext(), "Откликнуться", Toast.LENGTH_SHORT).show()
            }
            seeMore = count
            onSeeMoreClickListener = {
                findNavController().navigate(R.id.action_searchFragment_to_searchAllVacanciesFragment)
            }
        }
}