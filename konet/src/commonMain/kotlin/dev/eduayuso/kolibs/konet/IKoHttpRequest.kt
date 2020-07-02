package dev.eduayuso.kolibs.konet

import io.ktor.client.HttpClient
import io.ktor.client.request.HttpRequestBuilder
import kotlinx.serialization.KSerializer
import kotlinx.serialization.StringFormat

interface IKoHttpRequest {

    val httpClient: HttpClient
    val request: HttpRequestBuilder
    val json: StringFormat

    suspend fun <T> response(serializer: KSerializer<T>): T?
}