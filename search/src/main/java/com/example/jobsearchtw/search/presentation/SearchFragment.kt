package com.example.jobsearchtw.search.presentation

import android.os.Bundle
import android.view.View
import androidx.navigation.fragment.findNavController
import com.example.jobsearchtw.base.presentation.BaseFragment
import com.example.jobsearchtw.search.R
import com.example.jobsearchtw.search.databinding.FragmentSearchBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SearchFragment : BaseFragment<FragmentSearchBinding>(FragmentSearchBinding::inflate) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.moreVacancy.setOnClickListener {
            findNavController().navigate(R.id.action_searchFragment_to_searchVacancyFragment)
        }
    }

}