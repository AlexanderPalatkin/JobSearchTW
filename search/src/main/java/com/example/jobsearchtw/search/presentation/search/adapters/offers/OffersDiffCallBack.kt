package com.example.jobsearchtw.search.presentation.search.adapters.offers

import androidx.recyclerview.widget.DiffUtil
import com.example.jobsearchtw.core.domain.model.Offer

class OffersDiffCallBack : DiffUtil.ItemCallback<Offer>() {
    override fun areItemsTheSame(oldItem: Offer, newItem: Offer): Boolean =
        oldItem.title == newItem.title


    override fun areContentsTheSame(oldItem: Offer, newItem: Offer): Boolean =
        oldItem == newItem
}