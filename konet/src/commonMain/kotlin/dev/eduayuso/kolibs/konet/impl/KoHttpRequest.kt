package dev.eduayuso.kolibs.konet.impl

import dev.eduayuso.kolibs.konet.IKoHttpRequest
import io.ktor.client.HttpClient
import io.ktor.client.call.ReceivePipelineException
import io.ktor.client.request.HttpRequestBuilder
import io.ktor.client.request.request
import kotlinx.serialization.KSerializer
import kotlinx.serialization.StringFormat

class KoHttpRequest(

    http: HttpClient,
    request: HttpRequestBuilder,
    json: StringFormat

): IKoHttpRequest {

    override val httpClient = http
    override val request = request
    override val json = json

    override suspend fun <T> response(serializer: KSerializer<T>): T? {

        try {
            val result: String = httpClient.request(this.request)
            return this.json.parse(serializer, result)
        } catch (pipeline: ReceivePipelineException) {
            throw pipeline.cause
        }
    }
}