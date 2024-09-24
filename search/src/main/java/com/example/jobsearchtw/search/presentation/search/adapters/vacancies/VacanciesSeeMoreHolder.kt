package com.example.jobsearchtw.search.presentation.search.adapters.vacancies

import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.example.jobsearchtw.search.databinding.VacanciesSeeMoreItemBinding
import com.example.jobsearchtw.search.R

class VacanciesSeeMoreHolder(
    private val binding: VacanciesSeeMoreItemBinding,
    private val seeMore: Int,
    private val onSeeMoreClickListener: (() -> Unit)?
) : ViewHolder(binding.root) {
    fun bind() {
        binding.tvSeeMore.text = if (seeMore > 0) {
            binding.root.context.resources.getQuantityString(
                R.plurals.search_vacancy_count, seeMore, seeMore
            )
        } else {
            "Нет вакансий" // Текст по умолчанию
        }
        itemView.setOnClickListener {
            onSeeMoreClickListener?.invoke()
        }
    }
}