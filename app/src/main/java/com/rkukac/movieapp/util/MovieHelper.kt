package com.rkukac.movieapp.util

import android.content.Context
import androidx.annotation.StringRes
import com.rkukac.movieapp.R
import com.rkukac.movieapp.domain.model.DomainStateModel
import dagger.hilt.android.qualifiers.ApplicationContext
import timber.log.Timber
import java.text.DecimalFormat
import javax.inject.Inject

class MovieHelper @Inject constructor(
    @ApplicationContext private val context: Context
) {

    private val budgetFormat = DecimalFormat(context.getString(R.string.config_budget_format))

    private val budgetPrefix = context.getString(R.string.config_budget_prefix)

    fun getFormattedImage(image: String?): String? {
        return image?.let { context.getString(R.string.config_image_base_url).plus(it) }
    }

    fun getFormattedBudget(budget: Int): String {
        if (budget == 0) {
            return ""
        }

        return try {
            budgetPrefix.plus(budgetFormat.format(budget))
        } catch (e: Exception) {
            Timber.d(e, "Failed budget format!")
            ""
        }
    }

    //region Search
    fun getSearchErrorStateModel(): DomainStateModel = getDefaultErrorStateModel(buttonText = "")

    fun getSearchEmptyStateModel(): DomainStateModel = DomainStateModel(
        title = getStringResource(R.string.search_empty_title),
        description = getStringResource(R.string.search_empty_description),
        buttonText = ""
    )

    fun getEmptySearchKeywordMessage(): String =
        getStringResource(R.string.search_empty_keyword_message)
    //endregion

    private fun getDefaultErrorStateModel(
        buttonText: String = getStringResource(R.string.error_default_button_text)
    ): DomainStateModel = DomainStateModel(
        title = getStringResource(R.string.error_default_title),
        description = getStringResource(R.string.error_default_description),
        buttonText = buttonText
    )

    private fun getStringResource(@StringRes id: Int) = context.getString(id)
}