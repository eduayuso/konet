package com.konet.sample.library.domain.repository

import com.konet.sample.library.Constants
import com.konet.sample.library.domain.model.DDataUser
import com.konet.sample.library.domain.model.DUser
import com.konet.sample.library.domain.model.DUsersPage
import dev.eduayuso.kolibs.konet.impl.KoApiClient
import dev.eduayuso.kolibs.konet.impl.KoHttpRequest

class UsersRepository(api:KoApiClient): IRepository<KoApiClient> {

    override val api = api
    override val resourceUrl = Constants.Apis.Reqres.users

    private val url = resourceUrl // to use a shorter version

    suspend fun getAll(): DUsersPage? {

        return api.consume(url).get().response(DUsersPage.serializer())
    }

    suspend fun getPage(page:Int): DUsersPage? {

        val params = "page=${page}"
        return api.consume(url).get().with(params).response(DUsersPage.serializer())
    }

    suspend fun getById(id:Int): DDataUser? {

        return api.consume(url).get(id).response(DDataUser.serializer())
    }

    suspend fun createUser(user:DUser): DUser? {

        return api.consume(url).post(user, DUser.serializer()).response(DUser.serializer())
    }

    suspend fun updateUser(user:DUser): DUser? {

        return api.consume(url).put(user.id!!, user, DUser.serializer()).response(DUser.serializer())
    }

    suspend fun patchUser(user:DUser): DUser? {

        return api.consume(url).patch(user.id!!, user, DUser.serializer()).response(DUser.serializer())
    }

    suspend fun deleteUser(id:Int): DUser? {

        //return api.consume(url).delete(id).response(DUser.serializer())
        return null
    }
}