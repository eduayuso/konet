package com.konet.sample.library.domain.repository

import com.konet.sample.library.Constants
import com.konet.sample.library.SharedFactory
import com.konet.sample.library.domain.model.DDataUser
import com.konet.sample.library.domain.model.DUser
import com.konet.sample.library.domain.model.DUsersPage
import dev.eduayuso.kolibs.konet.IKoApiClient
import dev.eduayuso.kolibs.konet.impl.KoApiClient

class UsersRepository: IRepository<KoApiClient> {

    override val api = SharedFactory.testApi
    override val resourceUrl = Constants.Apis.Test.users

    private val url = resourceUrl // to use a shorter version

    suspend fun getAll(): DUsersPage? {

        return api.consume(url).get().response(DUsersPage.serializer())
    }

    suspend fun getById(id:Int): DDataUser? {

        return api.consume(url).get(id).response(DDataUser.serializer())
    }

    suspend fun updateUser(user:DUser): DUser? {

        return api.consume(url).put(user).response(DUser.serializer())
    }

    suspend fun createUser(user:DUser): DUser? {

        return api.consume(url).post(user).response(DUser.serializer())
    }
}