package com.konet.sample.library.di

import com.konet.sample.library.Constants
import com.konet.sample.library.domain.cache.impl.DataCache
import com.konet.sample.library.domain.repository.IAuthRepository
import com.konet.sample.library.domain.repository.impl.AuthRepository
import com.konet.sample.library.domain.repository.impl.UsersRepository
import dev.eduayuso.kolibs.konet.impl.DefaultKoApiClient
import dev.eduayuso.kolibs.konet.impl.KoApiClient
import dev.icerock.moko.network.features.TokenFeature
import io.ktor.client.HttpClient
import io.ktor.client.features.logging.LogLevel
import io.ktor.client.features.logging.Logger
import io.ktor.client.features.logging.Logging
import kotlinx.serialization.json.Json

/*
 * This must be a 'class', can't be an 'object' because in iOS the httpClient
 * can't be static nor stored in a static structure.
 * https://github.com/ktorio/ktor/issues/887
 */
class SharedFactory {

    /**
     * Default Api client instance with default Http client
     */
    private val reqresApi: DefaultKoApiClient by lazy {

        DefaultKoApiClient(
            baseUrl = Constants.Apis.Reqres.url
        )
    }

    private val _json =

        Json {
            encodeDefaults = false
            isLenient = true
            ignoreUnknownKeys = true
            allowSpecialFloatingPointValues = true
            useArrayPolymorphism = true
        }

    /**
     * Api client singleton with custom Http client and custom dynamic header
     */
    private val customReqresApi by lazy {

        object: KoApiClient() {
            override val json = _json
            override val baseUrl = Constants.Apis.Reqres.url
            override val httpClient = customHttpClient

            override fun getHeaders(): Map<String, String> =
                mapOf(
                    "CustomHeaderExample1" to "HeaderValue1", // it can be a function / dynamic value,
                    "CustomHeaderExample2" to "HeaderValue2"
                )
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

    val usersRepository: UsersRepository by lazy {

        UsersRepository(
            api = customReqresApi
        )
    }

    val authRepository: IAuthRepository by lazy {

        AuthRepository(
            api = customReqresApi
        )
    }

    val cache: DataCache by lazy {

        DataCache()
    }
}