package dev.eduayuso.kolibs.konet.impl

import dev.eduayuso.kolibs.konet.IKoRestConsumer
import io.ktor.client.call.ReceivePipelineException
import io.ktor.client.request.HttpRequestBuilder
import io.ktor.client.request.request
import io.ktor.http.HttpMethod
import io.ktor.http.takeFrom
import kotlinx.serialization.KSerializer
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.JsonConfiguration

class KoRestConsumer(
    api: KoApiClient,
    resourcePath: String

): IKoRestConsumer {

    override val baseUrl = api.baseUrl
    override val resourcePath = resourcePath
    override val httpClient = api.httpClient
    override val json = Json(JsonConfiguration(ignoreUnknownKeys = true))

    suspend fun <T> get(serializer: KSerializer<T>): T? {

        val builder = HttpRequestBuilder()

        builder.method = HttpMethod.Get
        builder.url {
            takeFrom(baseUrl)
            encodedPath = encodedPath.let { startingPath ->
                path(resourcePath)
                return@let startingPath + encodedPath.substring(1)
            }
        }
        with(builder.headers) {
            append("Accept", "application/json")
        }

        try {
            val result: String = httpClient.request(builder)
            return json.parse(serializer, result)
        } catch (pipeline: ReceivePipelineException) {
            throw pipeline.cause
        }
    }
}