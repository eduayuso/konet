package dev.eduayuso.kolibs.konet.impl

import dev.eduayuso.kolibs.konet.IKoHttpRequest
import io.ktor.client.HttpClient
import io.ktor.client.call.ReceivePipelineException
import io.ktor.client.request.HttpRequestBuilder
import io.ktor.client.request.request
import kotlinx.serialization.KSerializer
import kotlinx.serialization.StringFormat

private const val PARAM_DELIMITER = "="

class KoHttpRequest(

    http: HttpClient,
    request: HttpRequestBuilder,
    json: StringFormat

): IKoHttpRequest {

    override val httpClient = http
    override val request = request
    override val json = json

    override fun with(params:String): KoHttpRequest {

        val splitted = params.split(PARAM_DELIMITER)
        this.request.url.parameters.append(splitted[0],splitted[1])
        return this
    }

    override suspend fun <T> response(serializer: KSerializer<T>): T? {

        try {
            val result: String = httpClient.request(this.request)
            return this.json.parse(serializer, result)
        } catch (pipeline: ReceivePipelineException) {
            throw pipeline.cause
        }
    }
}