package dev.eduayuso.kolibs.konet.impl

import dev.eduayuso.kolibs.konet.IKoApiClient
import io.ktor.client.HttpClient

/**
 * @param url: API base URL
 * @param http: HTTP client
 * @param resources: a list that describes the resources (urls and types) of the API
 */
open class KoApiClient(
    url: String,
    http: HttpClient

): IKoApiClient {

    override val baseUrl = url
    override val httpClient = http

    fun consume(url:String): KoRestConsumer {
        return KoRestConsumer(this, url)
    }
}

