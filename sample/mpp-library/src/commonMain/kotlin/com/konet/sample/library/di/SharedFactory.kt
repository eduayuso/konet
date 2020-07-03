package com.konet.sample.library.di

import com.konet.sample.library.Constants
import com.konet.sample.library.domain.repository.UsersRepository
import dev.eduayuso.kolibs.konet.impl.KoApiClient
import io.ktor.client.HttpClient
import io.ktor.client.features.logging.LogLevel
import io.ktor.client.features.logging.Logger
import io.ktor.client.features.logging.Logging

object SharedFactory {

    private val customHttpClient by lazy {

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

    private val reqresApi by lazy {

        KoApiClient(url = Constants.Apis.Reqres.url)
    }

    val usersRepository: UsersRepository by lazy {

        UsersRepository(reqresApi)
    }
}