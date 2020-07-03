package dev.eduayuso.kolibs.konet

import io.ktor.client.HttpClient
import kotlinx.serialization.StringFormat

/**
 * @property baseUrl: API base url
 * @property resourcePath: API resource to consume
 * @property httpClient: Ktor HTTP client
 * @property json: JSON format
 */
interface IKoRestConsumer {

    val baseUrl: String
    val resourcePath: String
    val httpClient: HttpClient
    val json: StringFormat
}