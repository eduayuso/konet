package dev.eduayuso.kolibs.konet.impl

import dev.eduayuso.kolibs.konet.Defaults
import dev.eduayuso.kolibs.konet.IKoApiClient
import io.ktor.client.HttpClient

val defaults = Defaults()

open class KoApiClient(
    url: String,
    http: HttpClient

): IKoApiClient {

    constructor(url:String): this(url, defaults.httpClient)

    override val baseUrl = url
    override val httpClient = http

    fun consume(url:String): KoRestConsumer {
        return KoRestConsumer(this, url)
    }
}

