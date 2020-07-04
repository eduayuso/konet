package com.konet.sample.library.domain.repository

import com.konet.sample.library.domain.model.DAuthRequest
import com.konet.sample.library.domain.model.DAuthResponse
import dev.eduayuso.kolibs.konet.IKoApiClient

interface IAuthRepository: IRepository<IKoApiClient> {

    suspend fun login(auth: DAuthRequest): DAuthResponse?
}