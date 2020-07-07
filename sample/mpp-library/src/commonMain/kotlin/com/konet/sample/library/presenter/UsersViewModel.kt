package com.konet.sample.library.presenter

import com.konet.sample.library.domain.cache.IDataCache
import com.konet.sample.library.domain.model.DAuthRequest
import com.konet.sample.library.domain.model.DAuthResponse
import com.konet.sample.library.domain.model.DUser
import com.konet.sample.library.domain.repository.IAuthRepository
import com.konet.sample.library.domain.repository.IUsersRepository
import dev.icerock.moko.mvvm.livedata.LiveData
import dev.icerock.moko.mvvm.livedata.MutableLiveData
import dev.icerock.moko.mvvm.livedata.readOnly
import dev.icerock.moko.mvvm.viewmodel.ViewModel
import kotlinx.coroutines.launch

class UsersViewModel(

    private val cache: IDataCache,
    private val userRepo: IUsersRepository,
    private val authRepo: IAuthRepository

): ViewModel() {

    private val _users = MutableLiveData<List<DUser>?>(null)
    private val _authResult = MutableLiveData<DAuthResponse?>(null)

    val users: LiveData<List<DUser>?> = _users.readOnly()
    val authResult: LiveData<DAuthResponse?> = _authResult.readOnly()

    fun getAllUsers() {

        viewModelScope.launch {
            try {
                val userPage = userRepo.getAll()
                _users.value = userPage?.data
            } catch (error: Exception) {
                println("can't load $error")
            }
        }
    }

    fun getUsersPage(page: Int) {

        viewModelScope.launch {
            try {
                val userPage = userRepo.getPage(page)
                _users.value = userPage?.data
            } catch (error: Exception) {
                println("can't load $error")
            }
        }
    }

    fun getUser(id:Int) {

        viewModelScope.launch {
            try {
                val userData = userRepo.getById(id)
                _users.value = if (userData != null) listOf(userData.data) else emptyList()
            } catch (error: Exception) {
                println("can't load $error")
            }
        }
    }

    fun postUser(newUser: DUser) {

        viewModelScope.launch {
            try {
                val createdUser = userRepo.createUser(newUser)
                _users.value = if (createdUser != null) listOf(createdUser) else emptyList()
            } catch (error: Exception) {
                println("can't load $error")
            }
        }
    }

    fun putUser(user: DUser) {

        viewModelScope.launch {
            try {
                val updatedUser = userRepo.updateUser(user)
                _users.value = if (updatedUser != null) listOf(updatedUser) else emptyList()
            } catch (error: Exception) {
                println("can't load $error")
            }
        }
    }

    fun deleteUser(id: Int) {

        viewModelScope.launch {
            try {
                userRepo.deleteUser(id)
                _users.value = emptyList()
            } catch (error: Exception) {
                println("can't load $error")
            }
        }
    }

    fun login(auth: DAuthRequest) {

        viewModelScope.launch {
            try {
                val response = authRepo.login(auth)
                cache.authToken = response?.token
                _authResult.value = response
            } catch (error: Exception) {
                println("can't load $error")
                _authResult.value = DAuthResponse(token = null, error = "${error.message}")
            }
        }
    }

    fun parseResponse(res:DAuthResponse?): String =

        if (res?.token == null) res?.error ?: "" else "Token:${res.token}"

    fun parseUsers(users: List<DUser>?): String =
        users?.joinToString(separator = "\n") { "${it.id}: ${it.first_name} (${it.email})" } ?: ""
}