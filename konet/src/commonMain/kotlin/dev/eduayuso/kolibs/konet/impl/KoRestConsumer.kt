package dev.eduayuso.kolibs.konet.impl

import dev.eduayuso.kolibs.konet.Defaults
import dev.eduayuso.kolibs.konet.IKoHttpRequest
import dev.eduayuso.kolibs.konet.IKoRestConsumer
import dev.icerock.moko.network.LargeTextContent
import io.ktor.client.request.HttpRequestBuilder
import io.ktor.client.utils.EmptyContent
import io.ktor.http.ContentType
import io.ktor.http.HttpMethod
import io.ktor.http.takeFrom
import kotlinx.serialization.KSerializer
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.JsonConfiguration

class KoRestConsumer(
    api: KoApiClient,
    resourcePath: String

): IKoRestConsumer {

    override val baseUrl = api.baseUrl
    override val resourcePath = resourcePath
    override val httpClient = api.httpClient
    override val json = defaults.json

    fun get(): IKoHttpRequest {

        return this.buildRequest(HttpMethod.Get, this.resourcePath, EmptyContent)
    }

    fun get(id:Int): IKoHttpRequest {

        return this.buildRequest(HttpMethod.Get, "${this.resourcePath}/$id", EmptyContent)
    }

    fun <T> post(body:T, serializable: KSerializer<T>): IKoHttpRequest {

        val requestBody = this.buildRequestBody(body, serializable)
        return this.buildRequest(HttpMethod.Post, this.resourcePath, requestBody)
    }

    fun <T> put(id: Int, body: T, serializable: KSerializer<T>): IKoHttpRequest {

        val requestBody = this.buildRequestBody(body, serializable)
        return this.buildRequest(HttpMethod.Put, "${this.resourcePath}/$id", requestBody)
    }

    fun <T> put(body: T, serializable: KSerializer<T>): IKoHttpRequest {

        val requestBody = this.buildRequestBody(body, serializable)
        return this.buildRequest(HttpMethod.Put, resourcePath, requestBody)
    }

    fun <T> patch(id: Int, body: T, serializable: KSerializer<T>): IKoHttpRequest {

        val requestBody = this.buildRequestBody(body, serializable)
        return this.buildRequest(HttpMethod.Patch, "${this.resourcePath}/$id", requestBody)
    }

    fun <T> delete(body: T, serializable: KSerializer<T>): IKoHttpRequest {

        val requestBody = this.buildRequestBody(body, serializable)
        return this.buildRequest(HttpMethod.Delete, this.resourcePath, requestBody)
    }

    fun delete(id:Int): IKoHttpRequest {

        return this.buildRequest(HttpMethod.Delete, "${this.resourcePath}/$id", EmptyContent)
    }

    private fun <T> buildRequestBody(body: T, serializable: KSerializer<T>): LargeTextContent =

        LargeTextContent(
            json.stringify(serializable, body),
            ContentType.Application.Json.withoutParameters()
        )

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