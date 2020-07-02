/*
 * Copyright 2019 IceRock MAG Inc. Use of this source code is governed by the Apache 2.0 license.
 */

package com.konet.sample.library.viewmodel

import com.konet.sample.library.domain.model.DUsersPage
import com.konet.sample.library.domain.repository.UsersRepository
import dev.icerock.moko.mvvm.livedata.LiveData
import dev.icerock.moko.mvvm.livedata.MutableLiveData
import dev.icerock.moko.mvvm.livedata.readOnly
import dev.icerock.moko.mvvm.viewmodel.ViewModel
import kotlinx.coroutines.launch

class TestViewModel : ViewModel() {

    private val userRepo = UsersRepository() // may be injected

    private val _users = MutableLiveData<DUsersPage?>(null)

    val users: LiveData<DUsersPage?> = _users.readOnly()

    init {
        loadUsers()
    }

    fun onRefreshPressed() {
        loadUsers()
    }

    private fun loadUsers() {

        viewModelScope.launch {
            try {
                val userList = userRepo.getAll()
                _users.value = userList
            } catch (error: Exception) {
                println("can't load $error")
            }
        }
    }
}
