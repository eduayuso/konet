package com.konet.sample.library.presenter

import com.konet.sample.library.di.SharedFactory
import com.konet.sample.library.domain.model.DUser
import dev.icerock.moko.mvvm.livedata.LiveData
import dev.icerock.moko.mvvm.livedata.MutableLiveData
import dev.icerock.moko.mvvm.livedata.readOnly
import dev.icerock.moko.mvvm.viewmodel.ViewModel
import kotlinx.coroutines.launch

class LoginViewModel: ViewModel() {

    private val authRepo = SharedFactory.authRepository
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
}