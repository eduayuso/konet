package com.konet.sample.library.di

import com.konet.sample.library.Constants
import com.konet.sample.library.domain.cache.impl.DataCache
import com.konet.sample.library.domain.repository.IAuthRepository
import com.konet.sample.library.domain.repository.IUsersRepository
import com.konet.sample.library.domain.repository.impl.AuthRepository
import com.konet.sample.library.domain.repository.impl.UsersRepository
import dev.eduayuso.kolibs.konet.impl.KoApiClient
import dev.icerock.moko.network.exceptionfactory.HttpExceptionFactory
import dev.icerock.moko.network.exceptionfactory.parser.ErrorExceptionParser
import dev.icerock.moko.network.exceptionfactory.parser.ValidationExceptionParser
import dev.icerock.moko.network.features.ExceptionFeature
import dev.icerock.moko.network.features.TokenFeature
import io.ktor.client.HttpClient
import io.ktor.client.features.logging.LogLevel
import io.ktor.client.features.logging.Logger
import io.ktor.client.features.logging.Logging
import io.ktor.http.HttpStatusCode
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.JsonConfiguration

/*
 * This must be a 'class', can't be an 'object' because in iOS the httpClient
 * can't be static nor stored in a static structure.
 * https://github.com/ktorio/ktor/issues/887
 */
class SharedFactory {

    private val json = Json(JsonConfiguration(ignoreUnknownKeys = true))

    private val defaultHttpClient by lazy {

        HttpClient {

            install(ExceptionFeature) {
                exceptionFactory = HttpExceptionFactory(
                    defaultParser = ErrorExceptionParser(json),
                    customParsers = mapOf(
                        HttpStatusCode.UnprocessableEntity.value to ValidationExceptionParser(json)
                    )
                )
            }
            install(Logging) {
                level = LogLevel.ALL
                logger = object : Logger {
                    override fun log(message: String) {
                        println(message)
                    }
                }
            }
            // disable standard BadResponseStatus - exceptionfactory do it for us
            expectSuccess = false
        }
    }

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