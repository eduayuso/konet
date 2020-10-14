package com.konet.app.users

import android.os.Bundle
import android.view.View
import android.widget.ProgressBar
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import com.konet.sample.library.di.SharedFactory
import com.konet.sample.library.domain.model.DAuthRequest
import com.konet.sample.library.domain.model.DUser
import com.konet.sample.library.presenter.UsersViewModel
import dev.icerock.moko.mvvm.getViewModel
import java.util.*

private val fakeUser = DUser(2, "test@konet.com", null)
private val fakeAuth = DAuthRequest(email = "eve.holt@reqres.in", password = "cityslicka")

class UsersActivity : AppCompatActivity() {

    private lateinit var usersTextView: TextView
    private lateinit var authTextView: TextView
    private lateinit var progressVar: ProgressBar

    val viewModel by lazy {

        getViewModel {

            val factory = SharedFactory()
            UsersViewModel(
                userRepo = factory.usersRepository,
                authRepo = factory.authRepository,
                cache = factory.cache
            )
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_users)
        usersTextView = findViewById(R.id.usersTextView)
        authTextView = findViewById(R.id.authTextView)
        progressVar = findViewById(R.id.progressBar)

        viewModel.users.ld().observe(this, Observer {
            response ->
            showProgress(false)
            usersTextView.text = viewModel.parseUsers(response)
        })

        viewModel.authResult.ld().observe(this, Observer {
            response ->
            showProgress(false)
            authTextView.text = viewModel.parseResponse(response)
        })
    }

    private fun showProgress(show: Boolean) {
        progressVar.visibility = if (show) View.VISIBLE else View.INVISIBLE
        usersTextView.visibility = if (show) View.INVISIBLE else View.VISIBLE
        authTextView.visibility = if (show) View.INVISIBLE else View.VISIBLE
    }

    fun getUsersClick(view: View) {
        showProgress(true)
        viewModel.getAllUsers()
    }

    fun getUsersPageClick(view: View) {
        showProgress(true)
        viewModel.getUsersPage(2)
    }

    fun getUserClick(view: View) {
        showProgress(true)
        viewModel.getUser(2)
    }

    fun postUsersClick(view: View) {
        showProgress(true)
        viewModel.postUser(fakeUser)
    }

    fun putUsersClick(view: View) {
        showProgress(true)
        viewModel.putUser(fakeUser)
    }

    fun deleteUsersClick(view: View) {
        showProgress(true)
        viewModel.deleteUser(2)
    }

    fun loginClick(view: View) {
        showProgress(true)
        viewModel.login(fakeAuth)
    }
}