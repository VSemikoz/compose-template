package com.example.compose_template.view.model.common

import androidx.annotation.StringRes
import com.example.compose_template.R

sealed class UiError(
    override val message: String? = null,
    @StringRes val messageRes: Int = R.string.unknown_error_message
) : Throwable(message) {
    data object UnknownHostException : UiError(
        messageRes = R.string.unknown_host_error_message
    )

    data object RouteIsNull : UiError(messageRes = R.string.route_is_null_error_message)

    data class Common(override val message: String? = null) : UiError()
}