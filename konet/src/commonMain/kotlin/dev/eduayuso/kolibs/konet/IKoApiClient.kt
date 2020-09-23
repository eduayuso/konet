package dev.eduayuso.kolibs.konet

import io.ktor.client.HttpClient

/**
 * @property url: API base URL
 * @property http: Ktor HTTP client
 */
interface IKoApiClient {

    val baseUrl: String
    val httpClient: HttpClient
    fun getHeaders(): Map<String, String>
}