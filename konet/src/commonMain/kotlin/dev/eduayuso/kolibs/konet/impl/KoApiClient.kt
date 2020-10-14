package dev.eduayuso.kolibs.konet.impl

import dev.eduayuso.kolibs.konet.IKoApiClient

abstract class KoApiClient: IKoApiClient {

    fun consume(url:String): KoRestConsumer {
        return KoRestConsumer(this, url)
    }
}

