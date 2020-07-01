package dev.eduayuso.kolibs.konet

import dev.eduayuso.kolibs.konet.impl.KoApiClient
import io.ktor.client.HttpClient
import kotlinx.serialization.StringFormat
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.JsonConfiguration

interface IKoRestConsumer {

    val baseUrl: String
    val resourcePath: String
    val httpClient: HttpClient
    val json: StringFormat
}