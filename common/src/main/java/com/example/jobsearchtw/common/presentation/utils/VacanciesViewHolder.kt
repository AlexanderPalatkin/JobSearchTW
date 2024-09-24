package com.example.jobsearchtw.common.presentation.utils

import androidx.core.view.isVisible
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.example.jobsearchtw.common.R
import com.example.jobsearchtw.common.databinding.VacancyItemBinding
import com.example.jobsearchtw.core.domain.model.Vacancy
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

class VacanciesViewHolder(
    private val binding: VacancyItemBinding,
    private val onCardClickListener: (() -> Unit)? = null,
    private val onRespondClickListener: (() -> Unit)? = null,
) : ViewHolder(binding.root) {

    fun bind(vacancy: Vacancy) = vacancy.run {
        if (lookingNumber > 0) {
            val configuration = binding.root.context.resources.configuration
            configuration.setLocale(Locale("ru", "RU"))
            val localizedContext = binding.root.context.createConfigurationContext(configuration)
            binding.tvLookingNumber.text = localizedContext.resources.getQuantityString(
                R.plurals.observers_count, vacancy.lookingNumber, vacancy.lookingNumber
            )
        } else binding.tvLookingNumber.text= ""

        binding.tvTitle.text = title
        binding.tvTown.text = town
        binding.tvCompany.text = company
        binding.tvExperience.text = experience
        val publishedDateFormatted = formatPublishedDate(publishedDate)
        binding.tvPublishedDate.text = publishedDateFormatted
        var isFavoriteIconActive = isFavorite
        binding.ivFavorites.setImageResource(
            if (isFavorite) R.drawable.ic_heart_active else R.drawable.ic_heart_no_active
        )
        binding.tvSalary.text = salary
        binding.tvSalary.isVisible = salary.isNotEmpty()
        binding.linearCardInfo.setOnClickListener { onCardClickListener?.invoke() }
        binding.btnRespondVacancy.setOnClickListener { onRespondClickListener?.invoke() }
        binding.ivFavorites.setOnClickListener {
            isFavoriteIconActive = !isFavoriteIconActive

            binding.ivFavorites.setImageResource(
                if (isFavoriteIconActive) R.drawable.ic_heart_active else R.drawable.ic_heart_no_active
            )
        }
    }

    private fun formatPublishedDate(publishedDate: String): String {

        val inputFormat = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
        val date = inputFormat.parse(publishedDate)

        date?.let {
            val day = SimpleDateFormat("d", Locale.getDefault()).format(it)
            val month = getDeclinedMonth(it)

            return "Опубликовано $day $month"
        }
        return "Опубликовано"
    }

    private fun getDeclinedMonth(date: Date): String {
        val months = arrayOf(
            "января", "февраля", "марта", "апреля", "мая", "июня",
            "июля", "августа", "сентября", "октября", "ноября", "декабря"
        )

        val monthIndex = SimpleDateFormat("M", Locale.getDefault()).format(date).toInt() - 1
        return months[monthIndex]
    }
}