package com.konet.sample.library.domain.repository.impl

import com.konet.sample.library.Constants
import com.konet.sample.library.domain.model.DDataUser
import com.konet.sample.library.domain.model.DUser
import com.konet.sample.library.domain.model.DUsersPage
import com.konet.sample.library.domain.repository.IUsersRepository
import dev.eduayuso.kolibs.konet.impl.KoApiClient

class UsersRepository(
    override var api:KoApiClient

): IUsersRepository {

    override var resourceUrl = Constants.Apis.Reqres.users

    private var url = resourceUrl // to use a shorter version

    override suspend fun getAll(): DUsersPage? {

        return api.consume(url).get().response(DUsersPage.serializer())
    }

    override suspend fun getPage(page:Int): DUsersPage? {

        var params = "page=${page}"
        return api.consume(url).get().with(params).response(DUsersPage.serializer())
    }

    override suspend fun getById(id:Int): DDataUser? {

        return api.consume(url).get(id).response(DDataUser.serializer())
    }

    override suspend fun createUser(user:DUser): DUser? {

        return api.consume(url).post(user, DUser.serializer()).response(DUser.serializer())
    }

    override suspend fun updateUser(user:DUser): DUser? {

        return api.consume(url).put(user.id!!, user, DUser.serializer()).response(DUser.serializer())
    }

    override suspend fun patchUser(user:DUser): DUser? {

        return api.consume(url).patch(user.id!!, user, DUser.serializer()).response(DUser.serializer())
    }

    override suspend fun deleteUser(id:Int): DUser? {

        return api.consume(url).delete(id).response(null)
    }
}