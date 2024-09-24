package com.example.jobsearchtw.common.presentation.utils

import androidx.recyclerview.widget.DiffUtil
import com.example.jobsearchtw.core.domain.model.Vacancy

class VacanciesDiffCallBack : DiffUtil.ItemCallback<Vacancy>() {
    override fun areItemsTheSame(oldItem: Vacancy, newItem: Vacancy): Boolean =
        oldItem.id == newItem.id

    override fun areContentsTheSame(oldItem: Vacancy, newItem: Vacancy): Boolean =
        oldItem == newItem
}