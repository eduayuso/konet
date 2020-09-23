package dev.eduayuso.kolibs.konet.impl

class DefaultKoApiClient(

    override val baseUrl: String

) : KoApiClient() {

    override val httpClient = defaults.httpClient

    override fun getHeaders() = emptyMap<String, String>()
}