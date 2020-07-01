package com.konet.sample.library.domain.repository

import com.konet.sample.library.Constants
import com.konet.sample.library.SharedFactory
import com.konet.sample.library.domain.model.DUsersPage

class UsersRepository {

    val url = Constants.Apis.Test.users
    val api = SharedFactory.testApi

    suspend fun getAll(): DUsersPage? {

        return api.consume(url).get(DUsersPage.serializer())
    }
}