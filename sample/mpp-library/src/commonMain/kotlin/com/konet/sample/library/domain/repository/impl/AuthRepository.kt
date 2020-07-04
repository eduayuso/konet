package com.konet.sample.library.domain.repository.impl

import com.konet.sample.library.Constants
import com.konet.sample.library.domain.model.DAuthRequest
import com.konet.sample.library.domain.model.DAuthResponse
import com.konet.sample.library.domain.repository.IAuthRepository
import com.konet.sample.library.domain.repository.IRepository
import dev.eduayuso.kolibs.konet.impl.KoApiClient

class AuthRepository(api: KoApiClient): IAuthRepository {

    override val api = api
    override val resourceUrl = Constants.Apis.Reqres.login

    private val url = resourceUrl // to use a shorter version

    override suspend fun login(auth: DAuthRequest): DAuthResponse? {

        // POST /api/login
        return api.consume(url).post(auth, DAuthRequest.serializer()).response(DAuthResponse.serializer())
    }
}