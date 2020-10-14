package com.konet.sample.library.domain.model

import kotlinx.serialization.Serializable

@Serializable
data class DUser(
    var id: Int? = null,
    var email: String? = null,
    var first_name: String? = null
)