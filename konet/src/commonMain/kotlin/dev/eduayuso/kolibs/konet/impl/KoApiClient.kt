package dev.eduayuso.kolibs.konet.impl

import dev.eduayuso.kolibs.konet.Defaults
import dev.eduayuso.kolibs.konet.IKoApiClient
import io.ktor.client.HttpClient
import io.ktor.client.features.logging.LogLevel
import io.ktor.client.features.logging.Logger
import io.ktor.client.features.logging.Logging

open class KoApiClient(
    url: String,
    http: HttpClient

): IKoApiClient {

    constructor(url:String): this(url, Defaults.httpClient)

    override val baseUrl = url
    override val httpClient = http

    fun consume(url:String): KoRestConsumer {
        return KoRestConsumer(this, url)
    }
}

