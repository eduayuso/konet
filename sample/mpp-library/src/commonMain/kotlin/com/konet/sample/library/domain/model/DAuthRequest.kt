package com.konet.sample.library.domain.model

import kotlinx.serialization.Serializable

@Serializable
data class DAuthRequest(
    
    val email: String? = null,
    val password: String? = null
)