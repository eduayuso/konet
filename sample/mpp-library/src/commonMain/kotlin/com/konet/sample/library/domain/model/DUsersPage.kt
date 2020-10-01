package com.konet.sample.library.domain.model

import kotlinx.serialization.Serializable

@Serializable
data class DUsersPage(

    val page: Int? = null,
    val per_page: Int? = null,
    val total: Int? = null,
    val total_pages: Int? = null,
    val data: List<DUser>? = null
)