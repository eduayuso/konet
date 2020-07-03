package com.konet.sample.library.presenter

import com.konet.sample.library.di.SharedFactory
import com.konet.sample.library.domain.model.DUser
import com.konet.sample.library.domain.model.DUsersPage
import dev.icerock.moko.mvvm.livedata.LiveData
import dev.icerock.moko.mvvm.livedata.MutableLiveData
import dev.icerock.moko.mvvm.livedata.readOnly
import dev.icerock.moko.mvvm.viewmodel.ViewModel
import kotlinx.coroutines.launch

class UsersViewModel: ViewModel() {

    private val userRepo = SharedFactory.usersRepository

    private val _users = MutableLiveData<List<DUser>?>(null)

    val users: LiveData<List<DUser>?> = _users.readOnly()

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

    fun patchUser(user: DUser) {

        viewModelScope.launch {
            try {
                val updatedUser = userRepo.patchUser(user)
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
}