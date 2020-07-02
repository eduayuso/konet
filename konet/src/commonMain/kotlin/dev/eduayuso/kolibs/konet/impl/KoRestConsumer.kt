package dev.eduayuso.kolibs.konet.impl

import dev.eduayuso.kolibs.konet.IKoHttpRequest
import dev.eduayuso.kolibs.konet.IKoRestConsumer
import io.ktor.client.request.HttpRequestBuilder
import io.ktor.client.utils.EmptyContent
import io.ktor.http.HttpMethod
import io.ktor.http.takeFrom
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.JsonConfiguration

class KoRestConsumer(
    api: KoApiClient,
    resourcePath: String

): IKoRestConsumer {

    override val baseUrl = api.baseUrl
    override val resourcePath = resourcePath
    override val httpClient = api.httpClient
    override val json = Json(JsonConfiguration(ignoreUnknownKeys = true))

    fun get(): IKoHttpRequest {

        return this.buildRequest(HttpMethod.Get, this.resourcePath, EmptyContent)
    }

    fun get(id:Int): IKoHttpRequest {

        return this.buildRequest(HttpMethod.Get, "$this.resourcePath/$id", EmptyContent)
    }

    fun <T> put(body:T): IKoHttpRequest {

        return this.buildRequest(HttpMethod.Put, this.resourcePath, body!!)
    }

    fun <T> post(body:T): IKoHttpRequest {

        return this.buildRequest(HttpMethod.Post, this.resourcePath, body!!)
    }

    private fun buildRequest(method:HttpMethod, path:String, body: Any): IKoHttpRequest {

        val builder = HttpRequestBuilder()
        builder.method = method
        builder.body = body
        builder.url {
            takeFrom(baseUrl)
            encodedPath = encodedPath.let { startingPath ->
                path(path)
                return@let startingPath + encodedPath.substring(1)
            }
        }
        with(builder.headers) {
            append("Accept", "application/json")
        }

        return KoHttpRequest(
            this.httpClient,
            builder,
            this.json
        )
    }
}