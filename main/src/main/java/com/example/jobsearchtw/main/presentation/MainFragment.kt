package com.example.jobsearchtw.main.presentation

import android.os.Bundle
import android.view.View
import androidx.activity.OnBackPressedCallback
import androidx.core.content.ContextCompat
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import com.example.jobsearchtw.search.R.id.nav_graph_search
import com.example.jobsearchtw.base.presentation.BaseFragment
import com.example.jobsearchtw.favorites.R.id.nav_graph_favorites
import com.example.jobsearchtw.responses.R.id.nav_graph_responses
import com.example.jobsearchtw.messages.R.id.nav_graph_messages
import com.example.jobsearchtw.profile.R.id.nav_graph_profile
import com.example.jobsearchtw.main.R
import com.example.jobsearchtw.main.databinding.FragmentMainBinding
import com.google.android.material.badge.BadgeDrawable
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainFragment : BaseFragment<FragmentMainBinding>(FragmentMainBinding::inflate) {

    private lateinit var navController: NavController

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupNavigation()
        setupBottomNavigation(navController)
        setupBadge(5)
        handleOnBackPressed()
    }

    private fun setupNavigation() {
        val navHostFragment =
            childFragmentManager.findFragmentById(R.id.fragmentContainer) as NavHostFragment
        navController = navHostFragment.navController
        navController.setGraph(R.navigation.nav_graph_main)
    }

    private fun setupBottomNavigation(navController: NavController) {
        binding.bottomNavigation.setOnItemSelectedListener { item ->
            when (item.itemId) {
                nav_graph_search -> {
                    resetToStartDestination(nav_graph_search, navController)
                    true
                }

                nav_graph_favorites -> {
                    resetToStartDestination(nav_graph_favorites, navController)
                    true
                }

                nav_graph_responses -> {
                    resetToStartDestination(nav_graph_responses, navController)
                    true
                }

                nav_graph_messages -> {
                    resetToStartDestination(nav_graph_messages, navController)
                    true
                }

                nav_graph_profile -> {
                    resetToStartDestination(nav_graph_profile, navController)
                    true
                }

                else -> false
            }
        }
    }

    private fun resetToStartDestination(graphId: Int, navController: NavController) {
        if (navController.currentDestination?.id != graphId) {
            navController.popBackStack(navController.graph.startDestinationId, false)
        }
        navController.navigate(graphId)
    }

    private fun setupBadge(count: Int) {
        val bottomNavigationView = binding.bottomNavigation
        val badge: BadgeDrawable = bottomNavigationView.getOrCreateBadge(nav_graph_favorites)

        if (count > 0) {
            badge.isVisible = true
            badge.number = count
        } else {
            badge.isVisible = false
        }

        badge.badgeTextColor =
            ContextCompat.getColor(requireContext(), com.example.jobsearchtw.uikit.R.color.white)
        badge.backgroundColor =
            ContextCompat.getColor(requireContext(), com.example.jobsearchtw.uikit.R.color.red)
    }

    private fun handleOnBackPressed() {
        requireActivity().onBackPressedDispatcher.addCallback(viewLifecycleOwner,
            object : OnBackPressedCallback(true) {
                override fun handleOnBackPressed() {
                    if (!navController.popBackStack()) {
                        // Если стек фрагментов пуст, можно завершить активити или добавить другую логику
                        requireActivity().finish()
                    }
                }
            })
    }
}