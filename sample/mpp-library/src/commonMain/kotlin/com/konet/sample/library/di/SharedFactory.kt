package com.konet.sample.library.di

import com.konet.sample.library.Constants
import com.konet.sample.library.domain.cache.impl.DataCache
import com.konet.sample.library.domain.repository.IAuthRepository
import com.konet.sample.library.domain.repository.IUsersRepository
import com.konet.sample.library.domain.repository.impl.AuthRepository
import com.konet.sample.library.domain.repository.impl.UsersRepository
import dev.eduayuso.kolibs.konet.impl.KoApiClient
import dev.icerock.moko.network.features.TokenFeature
import io.ktor.client.HttpClient
import io.ktor.client.features.logging.LogLevel
import io.ktor.client.features.logging.Logger
import io.ktor.client.features.logging.Logging

object SharedFactory {

    private val customHttpClient by lazy {

        HttpClient {
            /**
             * Logging feature
             */
            install(Logging) {
                level = LogLevel.ALL
                logger = object : Logger {
                    override fun log(message: String) {
                        println(message)
                    }
                }
            }
            /**
             * TokenFeature lets you make authorization request using the IDataCache.token.
             * The token is set after successful login and this feature build the header
             * with the authorization header ("Authorization": "Bearer {token}")
             */
            install(TokenFeature) {
                tokenHeaderName = Constants.Headers.authorization
                tokenProvider = object : TokenFeature.TokenProvider {
                    override fun getToken(): String? {
                        val token = cache.authToken
                        if (token.isNullOrEmpty()) return ""
                        else return Constants.bearer + " " + token
                    }
                }
            }
        }
    }

    private val reqresApi by lazy {

        KoApiClient(url = Constants.Apis.Reqres.url)
        // Pass a customHttpClient to the api if you need an additional feature as TokenFeature
        // KoApiClient(url = Constants.Apis.Reqres.url, http = customHttpClient)
    }

    val usersRepository: IUsersRepository by lazy {

        UsersRepository(reqresApi)
    }

    val authRepository: IAuthRepository by lazy {

        AuthRepository(reqresApi)
    }

    val cache: DataCache by lazy {

        DataCache()
    }
}