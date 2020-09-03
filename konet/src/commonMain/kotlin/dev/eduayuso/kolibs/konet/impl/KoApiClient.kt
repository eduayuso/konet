package dev.eduayuso.kolibs.konet.impl

import dev.eduayuso.kolibs.konet.Defaults
import dev.eduayuso.kolibs.konet.IKoApiClient
import io.ktor.client.HttpClient

val defaults = Defaults()

open class KoApiClient(
    url: String,
    http: HttpClient,
    headers: Map<String, String>

): IKoApiClient {

    constructor(url: String, http: HttpClient):
        this(
            url = url,
            http = http,
            headers = mapOf("Accept" to "application/json")
        )

    constructor(url:String): this(url, defaults.httpClient)

    override val baseUrl = url
    override val httpClient = http
    override val httpHeaders = headers

    fun consume(url:String): KoRestConsumer {
        return KoRestConsumer(this, url)
    }
}

