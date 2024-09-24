package com.example.jobsearchtw.search.presentation.search.adapters.offers

import androidx.core.view.isVisible
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.example.jobsearchtw.core.domain.model.Offer
import com.example.jobsearchtw.common.R
import com.example.jobsearchtw.search.databinding.OfferItemBinding

class OffersViewHolder(
    private val binding: OfferItemBinding,
    private val onOfferClickListener: ((String) -> Unit)? = null
) : ViewHolder(binding.root) {

    fun bind(offer: Offer) = offer.run {
        if (id.isNotEmpty()) {
            binding.ivIdAvatar.setImageResource(getIconForId(id))
        }
        binding.tvTitle.text = title.trim()
        binding.tvLifting.isVisible = button.isNotEmpty()
        itemView.setOnClickListener { onOfferClickListener?.invoke(link) }
    }

    private fun getIconForId(id: String): Int {
        return when (id) {
            "near_vacancies" -> R.drawable.ic_near_vacancies
            "level_up_resume" -> R.drawable.ic_level_up_resume
            "temporary_job" -> R.drawable.ic_temporary_job
            else -> 0
        }
    }
}