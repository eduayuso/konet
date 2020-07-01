package dev.eduayuso.kolibs.konet

import io.ktor.client.HttpClient

interface IKoApiClient {

    val baseUrl: String
    val httpClient: HttpClient
}