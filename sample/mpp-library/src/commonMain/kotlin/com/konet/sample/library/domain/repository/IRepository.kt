package com.konet.sample.library.domain.repository

import dev.eduayuso.kolibs.konet.IKoApiClient

/**
 * @property api: The API the repository is about to consume
 * @property resourceUrl: Each repository represents the CRUD operations over an API resource
 */
interface IRepository<ApiType: IKoApiClient> {

    val api: ApiType
    val resourceUrl: String
}