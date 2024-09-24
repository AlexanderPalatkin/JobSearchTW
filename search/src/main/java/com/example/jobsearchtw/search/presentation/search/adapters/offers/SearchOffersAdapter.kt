package com.example.jobsearchtw.search.presentation.search.adapters.offers

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import com.example.jobsearchtw.core.domain.model.Offer
import com.example.jobsearchtw.search.databinding.OfferItemBinding

class SearchOffersAdapter : ListAdapter<Offer, OffersViewHolder>(OffersDiffCallBack()) {

    var onOfferClickListener: ((String) -> Unit)? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): OffersViewHolder {
        return OffersViewHolder(
            OfferItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            ), onOfferClickListener
        )
    }

    override fun onBindViewHolder(holder: OffersViewHolder, position: Int) {
        val offer = getItem(position)
        holder.bind(offer)
    }
}