package com.konet.sample.library.domain.repository

import com.konet.sample.library.domain.model.DDataUser
import com.konet.sample.library.domain.model.DUser
import com.konet.sample.library.domain.model.DUsersPage
import dev.eduayuso.kolibs.konet.IKoApiClient

interface IUsersRepository: IRepository<IKoApiClient> {

    suspend fun getAll(): DUsersPage?
    suspend fun getPage(page:Int): DUsersPage?
    suspend fun getById(id:Int): DDataUser?
    suspend fun createUser(user: DUser): DUser?
    suspend fun updateUser(user:DUser): DUser?
    suspend fun patchUser(user:DUser): DUser?
    suspend fun deleteUser(id:Int): DUser?
}