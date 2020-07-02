package com.konet.sample.library

import com.konet.sample.library.domain.model.DUser
import dev.eduayuso.kolibs.konet.impl.KoApiClient
import io.ktor.client.HttpClient
import io.ktor.client.features.logging.LogLevel
import io.ktor.client.features.logging.Logger
import io.ktor.client.features.logging.Logging

object SharedFactory {

    private val httpClient by lazy {

        HttpClient {
            install(Logging) {
                level = LogLevel.ALL
                logger = object : Logger {
                    override fun log(message: String) {
                        println(message)
                    }
                }
            }
        }
    }

    val testApi by lazy {

        KoApiClient(
            url = Constants.Apis.Test.url,
            http = httpClient
        )
    }
}