package com.konet.sample.library.domain.model

import kotlinx.serialization.Serializable

@Serializable
data class DAnotherEntity(
    var id: Float?,
    var foo: String?
)