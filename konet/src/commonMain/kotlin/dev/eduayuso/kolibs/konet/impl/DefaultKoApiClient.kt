package dev.eduayuso.kolibs.konet.impl

import dev.icerock.moko.network.exceptionfactory.HttpExceptionFactory
import dev.icerock.moko.network.exceptionfactory.parser.ErrorExceptionParser
import dev.icerock.moko.network.exceptionfactory.parser.ValidationExceptionParser
import dev.icerock.moko.network.features.ExceptionFeature
import io.ktor.client.*
import io.ktor.client.features.logging.*
import io.ktor.http.*
import kotlinx.serialization.json.Json

class DefaultKoApiClient(

    override var baseUrl: String

) : KoApiClient() {

    override val json =
        Json {
            encodeDefaults = false
            isLenient = true
            ignoreUnknownKeys = true
            allowSpecialFloatingPointValues = true
            useArrayPolymorphism = true
        }

    override val httpClient =

        HttpClient {

            install(ExceptionFeature) {
                exceptionFactory = HttpExceptionFactory(
                    defaultParser = ErrorExceptionParser(json),
                    customParsers = mapOf(
                        HttpStatusCode.UnprocessableEntity.value to ValidationExceptionParser(
                            json
                        )
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

    override fun getHeaders() = emptyMap<String, String>()
}