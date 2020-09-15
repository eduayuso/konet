package dev.eduayuso.kolibs.konet.impl

import dev.eduayuso.kolibs.konet.Defaults
import dev.eduayuso.kolibs.konet.IKoApiClient
import io.ktor.client.HttpClient

val defaults = Defaults()

abstract class KoApiClient: IKoApiClient {

    fun consume(url:String): KoRestConsumer {
        return KoRestConsumer(this, url)
    }
}

