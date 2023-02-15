package com.rkukac.movieapp.ui.model

import com.rkukac.movieapp.domain.model.DomainStateModel

data class UiStateModel(
    val title: String = "",
    val description: String = "",
    val buttonText: String = ""
)

fun DomainStateModel.toUiStateModel() = UiStateModel(
    title = title,
    description = description,
    buttonText = buttonText
)