package com.example.jobsearchtw.common.presentation.utils

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import com.example.jobsearchtw.common.databinding.VacancyItemBinding
import com.example.jobsearchtw.core.domain.model.Vacancy

class VacanciesAdapter() :
    ListAdapter<Vacancy, VacanciesViewHolder>(VacanciesDiffCallBack()) {

    var onCardClickListener: (() -> Unit)? = null
    var onRespondClickListener: (() -> Unit)? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VacanciesViewHolder {
        return VacanciesViewHolder(
            VacancyItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            ), onCardClickListener, onRespondClickListener
        )
    }

    override fun onBindViewHolder(holder: VacanciesViewHolder, position: Int) {
        val vacancy = getItem(position)
        holder.bind(vacancy)
    }
}