package com.konet.sample.library.domain.model

import kotlinx.serialization.Serializable

@Serializable
data class DUsersPage(

    val page: Int?,
    val per_page: Int?,
    val total: Int?,
    val total_pages: Int?,
    val data: List<DUser>?
)