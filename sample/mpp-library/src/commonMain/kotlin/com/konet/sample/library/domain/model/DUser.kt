package com.konet.sample.library.domain.model

import kotlinx.serialization.Serializable

@Serializable
data class DUser(
    var id: Int?,
    var email: String?,
    var first_name: String?,
    var last_name: String?,
    var avatar: String?
)