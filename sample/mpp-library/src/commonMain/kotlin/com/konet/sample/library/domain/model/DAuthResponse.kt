package com.konet.sample.library.domain.model

import kotlinx.serialization.Serializable

@Serializable
data class DAuthResponse(
    /*
     * You need to set a default value to avoid the MissingFieldException
     * if json response not have the field
     */
    val token: String? = "",
    val error: String? = ""
)