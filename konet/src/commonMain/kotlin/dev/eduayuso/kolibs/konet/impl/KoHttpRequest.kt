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

    override val httpClient: HttpClient,
    override val request: HttpRequestBuilder,
    override val json: StringFormat

): IKoHttpRequest {

    override fun with(params:String): KoHttpRequest {

        val splitted = params.split(PARAM_DELIMITER)
        this.request.url.parameters.append(splitted[0],splitted[1])
        return this
    }

    override suspend fun <T> response(serializer: KSerializer<T>?): T? {

        try {
            val result: String = httpClient.request(this.request)
            return if (serializer == null) null
            else this.json.decodeFromString(serializer, result)
        } catch (pipeline: ReceivePipelineException) {
            throw pipeline.cause
        }
    }
}