package dev.eduayuso.kolibs.konet

import io.ktor.client.HttpClient
import kotlinx.serialization.json.Json

/**
 * @property url: API base URL
 * @property http: Ktor HTTP client
 */
interface IKoApiClient {

    val json: Json
    val baseUrl: String
    val httpClient: HttpClient
    fun getHeaders(): Map<String, String>
}