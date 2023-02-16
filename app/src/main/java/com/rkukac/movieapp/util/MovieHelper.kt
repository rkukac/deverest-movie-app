package com.rkukac.movieapp.util

import android.content.Context
import androidx.annotation.StringRes
import com.rkukac.movieapp.R
import com.rkukac.movieapp.domain.model.DomainStateModel
import dagger.hilt.android.qualifiers.ApplicationContext
import timber.log.Timber
import java.math.BigDecimal
import java.text.DecimalFormat
import javax.inject.Inject

class MovieHelper @Inject constructor(
    @ApplicationContext private val context: Context
) {

    private val amountFormat = DecimalFormat(context.getString(R.string.config_amount_format))

    private val amountPrefix = context.getString(R.string.config_amount_prefix)

    fun getFormattedImage(image: String?): String? {
        return image?.let { context.getString(R.string.config_image_base_url).plus(it) }
    }

    fun getFormattedAmount(amount: BigDecimal?): String {
        if (amount == null || amount == BigDecimal.ZERO) {
            return ""
        }

        return try {
            amountPrefix.plus(amountFormat.format(amount))
        } catch (e: Exception) {
            Timber.d(e, "Failed to format amount!")
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

    //region Details
    fun getDetailsErrorStateModel(): DomainStateModel = getDefaultErrorStateModel()
    //endregion

    //region Popular
    fun getPopularErrorStateModel(): DomainStateModel = getDefaultErrorStateModel()

    fun getPopularEmptyStateModel(): DomainStateModel = DomainStateModel(
        title = getStringResource(R.string.popular_empty_title),
        description = getStringResource(R.string.popular_empty_description),
        buttonText = ""
    )
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