package com.example.jobsearchtw.search.presentation.search.adapters.vacancies

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.example.jobsearchtw.common.databinding.VacancyItemBinding
import com.example.jobsearchtw.common.presentation.utils.VacanciesDiffCallBack
import com.example.jobsearchtw.core.domain.model.Vacancy
import com.example.jobsearchtw.search.databinding.VacanciesSeeMoreItemBinding

class SearchVacanciesAdapter :
    ListAdapter<Vacancy, ViewHolder>(VacanciesDiffCallBack()) {

    var onCardClickListener: (() -> Unit)? = null
    var onRespondClickListener: (() -> Unit)? = null
    var seeMore: Int = 0
    var onSeeMoreClickListener: (() -> Unit)? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder = LayoutInflater.from(parent.context).run {
        return@run when(viewType) {
            ITEM_VACANCY_TYPE -> com.example.jobsearchtw.common.presentation.utils.VacanciesViewHolder(
                VacancyItemBinding.inflate(this, parent, false),
                onCardClickListener,
                onRespondClickListener
            )
        ITEM_SEE_MORE_TYPE -> VacanciesSeeMoreHolder(VacanciesSeeMoreItemBinding.inflate(this, parent, false), seeMore, onSeeMoreClickListener)
        else -> throw IllegalArgumentException("Unsupported view type: $viewType")
        }
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        when(holder.itemViewType) {
            ITEM_VACANCY_TYPE -> bindVacancies(holder, getItem(position))
            else -> bindSeeMoore(holder)
        }
    }

    override fun getItemCount(): Int {
        return minOf(super.getItemCount(), 4)
    }

    override fun getItemViewType(position: Int): Int = if (position == itemCount - 1) ITEM_SEE_MORE_TYPE else ITEM_VACANCY_TYPE

    private fun bindVacancies(holder: ViewHolder, vacancy: Vacancy) = (holder as com.example.jobsearchtw.common.presentation.utils.VacanciesViewHolder).bind(vacancy)
    private fun bindSeeMoore(holder: ViewHolder) = (holder as VacanciesSeeMoreHolder).bind()

    companion object {
        private const val ITEM_VACANCY_TYPE = -1
        private const val ITEM_SEE_MORE_TYPE = -2
    }
}