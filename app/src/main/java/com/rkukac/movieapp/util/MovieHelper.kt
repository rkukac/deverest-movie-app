package com.rkukac.movieapp.util

import android.content.Context
import androidx.annotation.StringRes
import com.rkukac.movieapp.R
import com.rkukac.movieapp.domain.model.DomainStateModel
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

class MovieHelper @Inject constructor(
    @ApplicationContext private val context: Context
) {

    fun getFormattedImage(image: String?): String? {
        return image?.let { context.getString(R.string.config_image_base_url).plus(it) }
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